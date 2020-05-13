package ru.litvinov.getPostParser.FSSPparser.core;

import ru.litvinov.getPostParser.FSSPparser.models.getResponse.GetResponse;


import ru.litvinov.getPostParser.FSSPparser.models.result.GetResult;
import ru.litvinov.getPostParser.utils.jsonUtils.JsonUtils;
import ru.litvinov.getPostParser.utils.requestUtils.RequestUtils;

import java.net.URLEncoder;

public class LogicIp implements Logic {

    String token;

    public LogicIp(String token){
        this.token = token;
    }

    @Override
    public GetResponse sendGet(String numip) {
        String s = String.format("https://api-ip.fssprus.ru/api/v1.0/search/ip?token=%s&number=%s",token, URLEncoder.encode(numip));
        GetResponse getResponse = (GetResponse) JsonUtils.jsonToObject(RequestUtils.getRequest(s),GetResponse.class);
        return getResponse;
    }

    @Override
    public GetResult takeResult(String task) {
        String s = String.format("https://api-ip.fssprus.ru/api/v1.0/result?token=%s&task=%s",token,task);
        System.out.println(s);
        GetResult getResult = (GetResult) JsonUtils.jsonToObject(RequestUtils.getRequest(s),GetResult.class);
        return getResult;
    }

    @Override
    public String sendPost(String url, String body) {
        return null;
    }
}
