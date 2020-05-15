package ru.litvinov.getPostParser.FSSPparser.core;

import ru.litvinov.getPostParser.FSSPparser.models.getResponse.GetResponse;
import ru.litvinov.getPostParser.FSSPparser.models.postRequest.PostRequest;
import ru.litvinov.getPostParser.FSSPparser.models.result.GetResult;

import java.util.ArrayList;
import java.util.List;

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
    public abstract GetResponse sendPostProcessor(PostRequest postRequest) throws Exception;
    public abstract void sendPosts() throws Exception;

    //Получаем результаты
    public List<GetResult> getResults(List<String> inputList) throws Exception {
        List<GetResult> resultList = new ArrayList<>();
        for (int i = 0; i < inputList.size(); i++) {
            GetResult getResult = (GetResult) logic.takeResult(inputList.get(i), token);
            resultList.add(getResult);
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
}