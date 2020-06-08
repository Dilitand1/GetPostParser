package ru.litvinov.getPostParser.FSSPparser.core.cores;

import ru.litvinov.getPostParser.FSSPparser.core.cache.CacheWork;
import ru.litvinov.getPostParser.FSSPparser.core.logic.Logic;
import ru.litvinov.getPostParser.FSSPparser.models.getResponse.GetResponse;
import ru.litvinov.getPostParser.FSSPparser.models.postRequest.Params;
import ru.litvinov.getPostParser.FSSPparser.models.postRequest.PostRequest;
import ru.litvinov.getPostParser.FSSPparser.models.result.GetResult;
import ru.litvinov.getPostParser.FSSPparser.models.result.ResponseResult;
import ru.litvinov.getPostParser.FSSPparser.models.result.ResultResult;
import ru.litvinov.getPostParser.FSSPparser.models.result.ResultUnion;
import ru.litvinov.getPostParser.utils.fileUtils.FileUtils;
import ru.litvinov.getPostParser.utils.jsonUtils.JsonUtils;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class CoreFssp {
    private String token;
    private Logic logic;
    private String inputFile = "inputFile.txt";
    private String outputFailedFile = "outputFailedFile.txt";
    private String outputTaskFile = "outputTaskFile.txt";
    private String outputSuccessResultFile = "outputSuccessResultFile.txt";
    private String outputFailedResultFile = "outputFailedResultFile.txt";
    private CacheWork cacheWork;

    public CoreFssp() {
    }

    public CoreFssp(String token, Logic logic) {
        this.token = token;
        this.logic = logic;
    }

    public CoreFssp(String token, Logic logic, String inputFile, String outputFailedFile, String outputTaskFile, CacheWork cacheWork) {
        this.token = token;
        this.logic = logic;
        this.inputFile = inputFile;
        this.outputFailedFile = outputFailedFile;
        this.outputTaskFile = outputTaskFile;
        this.cacheWork = cacheWork;
    }

    public abstract List<PostRequest> createListObjectsForPost(List inputList);

    //Получаем результаты
    public List<GetResult> getResults() throws Exception {
        //читаем строки из файла. Берем только первый столбец
        List<String> inputList = Files.readAllLines(Paths.get(outputTaskFile), Charset.forName("UTF-8")).stream().map(x -> x.split("~")[0]).distinct().collect(Collectors.toList());
        List<GetResult> resultList = new ArrayList<>();
        for (int i = 0; i < inputList.size(); i++) {
            GetResult getResult = (GetResult) logic.takeResult(inputList.get(i), token);
            resultList.add(getResult);
            resultProcessor(inputList.get(i), getResult);//обработка результата
            System.out.println("Обработано " + (i + 1) + " из " + inputList.size());
        }
        return resultList;
    }

    public void sendPosts() throws Exception {
        List<String> listOfIpOrClients = Files.readAllLines(Paths.get(getInputFile())); //грузим все строки
        deleteCache(listOfIpOrClients);
        if (!listOfIpOrClients.isEmpty()) {
            List<PostRequest> requestList = createListObjectsForPost(listOfIpOrClients);
            List<GetResponse> responseList = new ArrayList<>();
            for (int i = 0; i < requestList.size(); i++) {
                String requestString = JsonUtils.objectToJson(requestList.get(i));
                GetResponse getResponse = (GetResponse) getLogic().sendPost(requestString);
                responseList.add(getResponse);
                System.out.println("Отправлено " + (Math.min((i + 1) * 50, listOfIpOrClients.size())) + " из " + listOfIpOrClients.size());
                //работаем с кэшем. Сохраняем каждые 10 итераций
                getCacheWork().writePostRequestResult(requestList.get(i), getResponse);//пишем в кэш
                if ((i+1)%10 == 0) {
                    getCacheWork().saveCache();//сохраняем кэш
                    getCacheWork().saveCasheToFile(getOutputTaskFile());//сохраняем в файл
                }
            }
        }
        getCacheWork().saveCache();//сохраняем кэш
        getCacheWork().saveCasheToFile(getOutputTaskFile());//сохраняем в файл
    }

    public void deleteCache(List<String> list){
        List<String> cacheList = new ArrayList<>();
        //проверяем что кэш есть
        if (!cacheWork.getCacheMap().isEmpty()) {
            //получаем первый элемент в листе и определяем его вид
            Params tmp = cacheWork.getCacheMap().keySet().iterator().next();
            if (tmp.getNumber() == null) {
                cacheList = cacheWork.getCacheMap().keySet().stream()
                        .map(x -> x.getLastname() + ";" + x.getFirstname() + ";" + x.getSecondname() + ";" + x.getBirthdate() + ";" + x.getRegion())
                        .collect(Collectors.toList());
            } else {
                cacheList = cacheWork.getCacheMap().keySet().stream()
                        .map(x->x.getNumber()).collect(Collectors.toList());
            }
            boolean isDeleted = list.removeAll(cacheList);//удаляем из списка то что уже загружено
            if (isDeleted)
                System.out.println("Найдена информация в кэше, к загрузке " + list.size());
        }
    }

    //Обрабатываем результаты
    //выходной файл
    //uuid;Дата запроса;status;code;exception;queryType;responseStatus;F;I;O;DR;region;numIp;errorMessage;name;ExeProduction;Details;Subject;Department;Bailiff;IPEnd
    public void resultProcessor(String uuid, GetResult result) {
        StringBuilder sb1 = new StringBuilder();
        sb1.append(uuid).append("~").append(new SimpleDateFormat("dd.MM.yyyy").format(new Date())).append("~")
                .append(result.getStatus()).append("~")
                .append(result.getCode()).append("~")
                .append(result.getException()).append("~");
        if (!result.getStatus().equals("success") || result.getResponse().getStatus() != 0L) {
            //Если результат не успешный то сразу пишем в ошибки
            FileUtils.writeFile(sb1.toString() + result.getResponse().getStatus() + "\n", getOutputFailedResultFile(), true);
            return;
        } else {
            //Если в респонзе что то есть то разбираем иначе пишем что пустой ответ
            ResponseResult responseResults[] = result.getResponse().getResult();
            if (responseResults.length == 0) {
                FileUtils.writeFile(sb1.toString() + "нет данных" + "\n", getOutputSuccessResultFile(), true);
            } else {
                //парсим массив
                for (ResponseResult responseResult : responseResults) {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(responseResult.getQuery().getType()).append("~")
                            .append(responseResult.getStatus()).append("~")
                            .append(responseResult.getQuery().getParams().toString()).append("~");
                    ResultUnion resultUnion = responseResult.getResult();
                    if (resultUnion.getErrorResult() != null) {
                        //Запрос с ошибкой
                        sb2.append(resultUnion.getErrorResult().getMessage()).append("~");
                        FileUtils.writeFile(sb1.toString() + sb2.toString() + "\n", getOutputSuccessResultFile(), true);
                    } else {
                        ResultResult resultResults[] = resultUnion.getSuccessResult();
                        sb2.append("~");//пустая колонка для ошибки
                        if (resultResults.length == 0) {
                            FileUtils.writeFile(sb1.toString() + sb2.toString() + "нет данных на сайте" + "\n", getOutputSuccessResultFile(), true);
                        }
                        for (ResultResult resultResult : resultResults) {
                            StringBuilder sb3 = new StringBuilder(sb2.toString());
                            sb3.append(resultResult.getName()).append("~")
                                    .append(resultResult.getExeProduction()).append("~")
                                    .append(resultResult.getDetails()).append("~")
                                    .append(resultResult.getSubject()).append("~")
                                    .append(resultResult.getDepartment()).append("~")
                                    .append(resultResult.getBailiff()).append("~")
                                    .append(resultResult.getIPEnd());
                            FileUtils.writeFile(sb1.toString().replaceAll("\n", "") + sb3.toString().replaceAll("\n", "") + "\n", getOutputSuccessResultFile(), true);
                        }
                    }
                }
            }
        }
    }


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Logic getLogic() {
        return logic;
    }

    public void setLogic(Logic logic) {
        this.logic = logic;
    }

    public String getInputFile() {
        return inputFile;
    }

    public void setInputFile(String inputFile) {
        this.inputFile = inputFile;
    }

    public String getOutputFailedFile() {
        return outputFailedFile;
    }

    public void setOutputFailedFile(String outputFailedFile) {
        this.outputFailedFile = outputFailedFile;
    }

    public String getOutputTaskFile() {
        return outputTaskFile;
    }

    public void setOutputTaskFile(String outputSuccessFile) {
        this.outputTaskFile = outputSuccessFile;
    }

    public CacheWork getCacheWork() {
        return cacheWork;
    }

    public void setCacheWork(CacheWork cacheWork) {
        this.cacheWork = cacheWork;
    }

    public String getOutputSuccessResultFile() {
        return outputSuccessResultFile;
    }

    public void setOutputSuccessResultFile(String outputSuccessResultFile) {
        this.outputSuccessResultFile = outputSuccessResultFile;
    }

    public String getOutputFailedResultFile() {
        return outputFailedResultFile;
    }

    public void setOutputFailedResultFile(String outputFailedResultFile) {
        this.outputFailedResultFile = outputFailedResultFile;
    }
}