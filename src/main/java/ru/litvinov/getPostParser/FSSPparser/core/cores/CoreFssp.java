package ru.litvinov.getPostParser.FSSPparser.core.cores;

import ru.litvinov.getPostParser.FSSPparser.core.cache.CacheWork;
import ru.litvinov.getPostParser.FSSPparser.core.logic.Logic;
import ru.litvinov.getPostParser.FSSPparser.models.result.GetResult;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
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

    public abstract void sendPosts() throws Exception;
    public abstract void resultProcessor(String uuid, GetResult result);

    //Получаем результаты
    public List<GetResult> getResults() throws Exception {
        //читаем строки из файла. Берем только первый столбец
        List<String> inputList = Files.readAllLines(Paths.get(outputTaskFile)).stream().map(x-> x.split("~")[0]).distinct().collect(Collectors.toList());
        List<GetResult> resultList = new ArrayList<>();
        for (int i = 0; i < inputList.size(); i++) {
            GetResult getResult = (GetResult) logic.takeResult(inputList.get(i), token);
            resultList.add(getResult);
            resultProcessor(inputList.get(i),getResult);//обработка результата
        }
        return resultList;
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