package ru.litvinov.getPostParser.FSSPparser.core.cores;

import ru.litvinov.getPostParser.FSSPparser.core.cache.CacheWorkerIp;
import ru.litvinov.getPostParser.FSSPparser.core.logic.Logic;
import ru.litvinov.getPostParser.FSSPparser.models.getResponse.GetResponse;
import ru.litvinov.getPostParser.FSSPparser.models.postRequest.ParamsIp;
import ru.litvinov.getPostParser.FSSPparser.models.postRequest.PostRequest;
import ru.litvinov.getPostParser.FSSPparser.models.postRequest.Request;
import ru.litvinov.getPostParser.FSSPparser.models.result.GetResult;
import ru.litvinov.getPostParser.FSSPparser.models.result.ResponseResult;
import ru.litvinov.getPostParser.FSSPparser.models.result.ResultResult;
import ru.litvinov.getPostParser.FSSPparser.models.result.ResultUnion;
import ru.litvinov.getPostParser.utils.fileUtils.FileUtils;
import ru.litvinov.getPostParser.utils.jsonUtils.JsonUtils;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CoreFsspIp extends CoreFssp {

    public CoreFsspIp() {

    }

    public CoreFsspIp(String token, Logic logic) {
        super(token, logic);
    }

    public CoreFsspIp(String token, Logic logic, String inputFile, String outputFailedFile, String outputSuccessFile, CacheWorkerIp cacheWorkerIp) {
        super(token, logic, inputFile, outputFailedFile, outputSuccessFile, cacheWorkerIp);
    }

    //формируем пост запросы
    public List<PostRequest> createListObjectsForPost(List inputList) {
        List<PostRequest> postRequests = new ArrayList<>();
        //режем на части по 50 штук.
        for (int i = 0; i <= inputList.size() / 50; i++) {
            PostRequest postRequest = new PostRequest();
            postRequest.setToken(getToken());
            List<Request> requests = new ArrayList<>();
            int counter = 0;
            for (int j = i * 50; j < inputList.size() && counter != 50; j++, counter++) {
                Request request1 = new Request();
                ParamsIp paramsIp = new ParamsIp();
                paramsIp.setNumber(inputList.get(j).toString());
                request1.setType(3);
                request1.setParams(paramsIp);
                requests.add(request1);
            }
            postRequest.setRequest(requests);
            postRequests.add(postRequest);
        }
        return postRequests;
    }

    //Обрабатываем результаты
    //выходной файл
    //uuid;Дата запроса;status;code;exception;queryType;responseStatus;numIp;errorMessage;name;ExeProduction;Details;Subject;Department;Bailiff;IPEnd
    public void resultProcessor(String uuid, GetResult result) {
        StringBuilder sb1 = new StringBuilder();
        sb1.append(uuid).append("~").append(new SimpleDateFormat("dd.MM.yyyy").format(new Date())).append("~")
                .append(result.getStatus()).append("~")
                .append(result.getCode()).append("~")
                .append(result.getException()).append("~");
        System.out.println(result.getResponse().getStatus());
        if (!result.getStatus().equals("success")) {
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
                            .append(responseResult.getQuery().getParams().getNumber()).append("~"); //номер ИП
                    ResultUnion resultUnion = responseResult.getResult();
                    if (resultUnion.getErrorResult() != null) {
                        //Запрос с ошибкой
                        sb2.append(resultUnion.getErrorResult().getMessage()).append("~");
                        FileUtils.writeFile(sb1.toString() + sb2.toString() + "\n", getOutputSuccessResultFile(), true);
                    } else {
                        ResultResult resultResults[] = resultUnion.getSuccessResult();
                        sb2.append("~");//пустая колонка для ошибки
                        if (resultResults.length == 0) {
                            FileUtils.writeFile(sb1.toString() + sb2.toString() + "нет данных на сайте" +"\n", getOutputSuccessResultFile(), true);
                        }
                        for (ResultResult resultResult : resultResults) {
                            sb2.append(resultResult.getName()).append("~")
                                    .append(resultResult.getExeProduction()).append("~")
                                    .append(resultResult.getDetails()).append("~")
                                    .append(resultResult.getSubject()).append("~")
                                    .append(resultResult.getDepartment()).append("~")
                                    .append(resultResult.getBailiff()).append("~")
                                    .append(resultResult.getIPEnd()).append("\n");
                            FileUtils.writeFile(sb1.toString() + sb2.toString(), getOutputSuccessResultFile(), true);
                        }
                    }
                }
            }
        }
    }

    //Направляем запросы
    @Override
    public void sendPosts() throws Exception {
        List<String> listOfIpOrClients = Files.readAllLines(Paths.get(getInputFile())); //грузим все строки
        //getCacheWork().init();//грузим кэш

        boolean isDeleted = listOfIpOrClients.removeAll(getCacheWork().getCacheMap().keySet());//удаляем из списка то что уже загружено
        if (isDeleted)
            System.out.println("Найдена информация в кэше, к загрузке " + listOfIpOrClients.size());

        if (!listOfIpOrClients.isEmpty()) {
            List<PostRequest> requestList = createListObjectsForPost(listOfIpOrClients);
            List<GetResponse> responseList = new ArrayList<>();
            for (int i = 0; i < requestList.size(); i++) {
                //GetResponse getResponse = sendPostProcessor(requestList.get(i));
                String requestString = JsonUtils.objectToJson(requestList.get(i));
                GetResponse getResponse = (GetResponse) getLogic().sendPost(requestString);
                responseList.add(getResponse);
                System.out.println("Отправлено " + (Math.min((i + 1) * 50, listOfIpOrClients.size()))  + " из " + listOfIpOrClients.size());
                //работаем с кэшем
                getCacheWork().writePostRequestResult(requestList.get(i), getResponse);//пишем в кэш
                getCacheWork().saveCache();//сохраняем кэш
            }
        }
        getCacheWork().saveCasheToFile(getOutputTaskFile());
    }
}
