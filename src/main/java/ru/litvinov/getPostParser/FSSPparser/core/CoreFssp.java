package ru.litvinov.getPostParser.FSSPparser.core;

import ru.litvinov.getPostParser.FSSPparser.models.getResponse.GetResponse;
import ru.litvinov.getPostParser.FSSPparser.models.postRequest.PostRequest;
import ru.litvinov.getPostParser.FSSPparser.models.result.GetResult;
import ru.litvinov.getPostParser.utils.jsonUtils.JsonUtils;

import java.util.ArrayList;
import java.util.List;

public abstract class CoreFssp {
    private String token;
    private Logic logic;

    public CoreFssp(String token,Logic logic) {
        this.token = token;
        this.logic = logic;
    }

    public abstract List<PostRequest> createListObjectsForPost(List inputList);

    //Направляем запросы
    public List<GetResponse> sendPosts(List inputList) throws Exception {
        List<PostRequest> requestList = createListObjectsForPost(inputList);
        List<GetResponse> responseList = new ArrayList<>();
        for (int i = 0;i < requestList.size();i++){
            String requestString = JsonUtils.objectToJson(requestList.get(i));
            GetResponse getResponse = (GetResponse) logic.sendPost(requestString);
            responseList.add(getResponse);
        }
        return responseList;
    }

    //Получаем результаты
    public List<GetResult> getResults(List<String> inputList) throws Exception {
        List<GetResult> resultList = new ArrayList<>();
        for (int i = 0; i < inputList.size();i++){
            GetResult getResult = (GetResult) logic.takeResult(inputList.get(i));
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
}
