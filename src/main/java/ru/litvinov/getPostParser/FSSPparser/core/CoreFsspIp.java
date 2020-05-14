package ru.litvinov.getPostParser.FSSPparser.core;

import ru.litvinov.getPostParser.FSSPparser.models.postRequest.ParamsIp;
import ru.litvinov.getPostParser.FSSPparser.models.postRequest.PostRequest;
import ru.litvinov.getPostParser.FSSPparser.models.postRequest.Request;

import java.util.Arrays;
import java.util.List;

public class CoreFsspIp extends CoreFssp {

    public CoreFsspIp(String token) {
        super(token);
    }

    @Override
    public List<PostRequest> createListObjectsForPost(List inputList) {
        return null;
    }

    @Override
    public List<Request> createListRequest(List list) {
        return null;
    }

    @Override
    public PostRequest createObject(List list) {
        ParamsIp params1 = new ParamsIp();
        params1.setNumber("93183/18/38035-ИП");

        ParamsIp params2 = new ParamsIp();
        params2.setNumber("19163/20/24026-ИП");

        Request request1 = new Request();
        request1.setType(3);
        request1.setParams(params1);

        Request request2 = new Request();
        request2.setType(3);
        request2.setParams(params2);

        List<Request> requests = Arrays.asList(request1,request2);

        PostRequest postRequest = new PostRequest();
        postRequest.setToken(super.getToken());
        postRequest.setRequest(requests);

        return postRequest;
    }

}
