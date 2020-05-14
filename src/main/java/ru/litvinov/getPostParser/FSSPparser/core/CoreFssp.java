package ru.litvinov.getPostParser.FSSPparser.core;

import ru.litvinov.getPostParser.FSSPparser.models.getResponse.GetResponse;
import ru.litvinov.getPostParser.FSSPparser.models.postRequest.PostRequest;
import ru.litvinov.getPostParser.FSSPparser.models.result.GetResult;
import ru.litvinov.getPostParser.utils.fileUtils.FileUtils;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public abstract class CoreFssp {
    private String token;
    private Logic logic;
    private String inputFile = "inputFile.txt";
    private String casheFile = "cacheFile.txt";
    private String outputFailedFile = "outputFailedFile.txt";
    private String outputSuccessFile = "outputSuccessFile.txt";
    private CacheWorkerIp cacheWorkerIp = new CacheWorkerIp(casheFile);

    public CoreFssp() {

    }

    public CoreFssp(String token, Logic logic) {
        this.token = token;
        this.logic = logic;
    }

    public CoreFssp(String token, Logic logic, String inputFile, String casheFile, String outputFailedFile, String outputSuccessFile, CacheWorkerIp cacheWorkerIp) {
        this.token = token;
        this.logic = logic;
        this.inputFile = inputFile;
        this.casheFile = casheFile;
        this.outputFailedFile = outputFailedFile;
        this.outputSuccessFile = outputSuccessFile;
        this.cacheWorkerIp = cacheWorkerIp;
    }

    public abstract List<PostRequest> createListObjectsForPost(List inputList);
    public abstract GetResponse sendPostProcessor(PostRequest postRequest) throws Exception;

    public void sendPostProcessing() throws Exception {
        List<String> listOfIp = Files.readAllLines(Paths.get(getInputFile()));
        List<GetResponse> responses = sendPosts(listOfIp);
        for (GetResponse response : responses) {
            FileUtils.writeFile(response.getResponse().getTask() + "~" + response.getStatus() + "~" + response.getCode() + "~" + response.getException(), getOutputSuccessFile(), true);
        }
    }

    //Направляем запросы
    public List<GetResponse> sendPosts(List inputList) throws Exception {
        List<PostRequest> requestList = createListObjectsForPost(inputList);
        List<GetResponse> responseList = new ArrayList<>();
        for (int i = 0; i < requestList.size(); i++) {
            GetResponse getResponse = sendPostProcessor(requestList.get(i));
            responseList.add(getResponse);
        }
        return responseList;
    }

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

    public String getOutputSuccessFile() {
        return outputSuccessFile;
    }

    public void setOutputSuccessFile(String outputSuccessFile) {
        this.outputSuccessFile = outputSuccessFile;
    }

    public String getCasheFile() {
        return casheFile;
    }

    public void setCasheFile(String casheFile) {
        this.casheFile = casheFile;
    }

    public CacheWorkerIp getCacheWorkerIp() {
        return cacheWorkerIp;
    }

    public void setCacheWorkerIp(CacheWorkerIp cacheWorkerIp) {
        this.cacheWorkerIp = cacheWorkerIp;
    }
}
