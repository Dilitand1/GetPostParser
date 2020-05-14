package ru.litvinov.getPostParser.FSSPparser;

import ru.litvinov.getPostParser.FSSPparser.core.Logic;
import ru.litvinov.getPostParser.FSSPparser.core.LogicIp;
import ru.litvinov.getPostParser.FSSPparser.models.getResponse.GetResponse;
import ru.litvinov.getPostParser.FSSPparser.models.postRequest.ParamsIp;
import ru.litvinov.getPostParser.FSSPparser.models.postRequest.PostRequest;
import ru.litvinov.getPostParser.FSSPparser.models.postRequest.Request;
import ru.litvinov.getPostParser.utils.jsonUtils.JsonUtils;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainFssp {

    static String token = "ZYrCH4sHnb89";
    static String token2 = "2oFwTlKyEa92";
    static String testIp = "93183/18/38035-ИП";
    static String test_uid = "081fc33b-106d-4ef2-982d-77a47da3f18a";

    static String tttbefore = "https://api-ip.fssprus.ru/api/v1.0/search/ip?token=ZYrCH4sHnb89&number=93183/18/38035-ИП";

    public static void main(String[] args) throws MalformedURLException, URISyntaxException {
        Logic logic = new LogicIp(token2);

        PostRequest postRequest = test();
        GetResponse getResponse = (GetResponse) logic.sendPost(JsonUtils.objectToJson(postRequest));
        System.out.println(getResponse.toString());
        //GetResponse result = (GetResponse) logic.sendGet(testIp);
    }

    public static PostRequest test(){
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
        postRequest.setToken(token2);
        postRequest.setRequest(requests);

        return postRequest;

    }
}
