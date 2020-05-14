package ru.litvinov.getPostParser.FSSPparser.core;

import ru.litvinov.getPostParser.FSSPparser.models.getResponse.GetResponse;


import ru.litvinov.getPostParser.FSSPparser.models.result.GetResult;
import ru.litvinov.getPostParser.utils.jsonUtils.JsonUtils;
import ru.litvinov.getPostParser.utils.requestUtils.RequestUtils;

import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class LogicIp implements Logic {

    private String token;

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
        GetResult getResult = (GetResult) JsonUtils.jsonToObject(RequestUtils.getRequest(s),GetResult.class);
        return getResult;
    }

    @Override
    public GetResponse sendPost(String body) {
        //задаем хедеры
        Map<String,String> map = new LinkedHashMap<String, String>();
        map.put("Content-Type", "application/json; utf-8");
        map.put("Accept", "application/json");
        //задаем url
        String url = "https://api-ip.fssprus.ru/api/v1.0/search/group";
        String responseString = RequestUtils.postRequest(url,body,map);
        return (GetResponse) JsonUtils.jsonToObject(responseString,GetResponse.class);
    }

    @Override
    public GetResponse sendPost(List params) {
        return null;
    }


    public String createBodyForPost(){
        return null;
    }
}
