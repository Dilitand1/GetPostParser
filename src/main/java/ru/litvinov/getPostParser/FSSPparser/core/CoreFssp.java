package ru.litvinov.getPostParser.FSSPparser.core;

import ru.litvinov.getPostParser.FSSPparser.models.getResponse.GetResponse;
import ru.litvinov.getPostParser.FSSPparser.models.postRequest.Params;
import ru.litvinov.getPostParser.FSSPparser.models.postRequest.ParamsIp;
import ru.litvinov.getPostParser.FSSPparser.models.postRequest.PostRequest;
import ru.litvinov.getPostParser.FSSPparser.models.postRequest.Request;

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
    public abstract PostRequest createObject(List list);
    public abstract List<Request> createListRequest(List list);

    public List<GetResponse> sendPosts(List inputList){
        List<PostRequest> requestList = createListObjectsForPost(inputList);
        List<GetResponse> responseList = new ArrayList<>();
        for (int i = 0;i < requestList.size();i++){
            responseList.add(logic.sendPost())
        }
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
