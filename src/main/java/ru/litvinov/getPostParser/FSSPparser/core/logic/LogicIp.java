package ru.litvinov.getPostParser.FSSPparser.core.logic;

import ru.litvinov.getPostParser.FSSPparser.models.getResponse.GetResponse;


import ru.litvinov.getPostParser.FSSPparser.models.result.GetResult;
import ru.litvinov.getPostParser.utils.jsonUtils.JsonUtils;
import ru.litvinov.getPostParser.utils.requestUtils.RequestUtils;

import java.net.URLEncoder;
import java.util.*;

public class LogicIp implements Logic {

    @Override
    public GetResponse sendGet(String numip, String token) throws Exception {
        String s = String.format("https://api-ip.fssprus.ru/api/v1.0/search/ip?token=%s&number=%s", token, URLEncoder.encode(numip));
        GetResponse getResponse = (GetResponse) JsonUtils.jsonToObject(RequestUtils.getRequest(s,new HashMap()), GetResponse.class);
        return getResponse;
    }

    @Override
    public GetResult takeResult(String task, String token) throws Exception {
        String s = String.format("https://api-ip.fssprus.ru/api/v1.0/result?token=%s&task=%s", token, task);

        GetResult getResult = null;
        try {
            Thread.sleep(100);
            getResult = (GetResult) JsonUtils.jsonToObject(RequestUtils.getRequest(s,new HashMap()), GetResult.class);

        } catch (Exception e){
            System.out.println(e.getMessage().split("\n")[0] + "\t"  + e.getMessage().split("\n")[1]);
            if (e.getMessage().contains("Too Many")) {
                //Если много запросов то ждем 30 сек
                System.out.println("Много запросов спим 1 минуту");
                Thread.sleep(60000);
                getResult = takeResult(task,token);
            } else {
                throw e;
            }
        }
        return getResult;
    }

    @Override
    public GetResponse sendPost(String body) throws Exception {
        //задаем хедеры
        Map<String, String> map = new LinkedHashMap<String, String>();
        map.put("Content-Type", "application/json; utf-8");
        map.put("Accept", "application/json");
        //задаем url
        String url = "https://api-ip.fssprus.ru/api/v1.0/search/group";
;
        GetResponse getResponse = null;
        try {
            //отправляем запрос
            String responseString = RequestUtils.postRequest(url, body, map);
            getResponse = (GetResponse) JsonUtils.jsonToObject(responseString, GetResponse.class);
            Thread.sleep(3000); //спим 2 секунду
        } catch (Exception e) {
            //если косяк то обрабатываем ошибку
            String[] splitMessage = e.getMessage().split("\n");
            Arrays.stream(splitMessage).limit(2).forEach(x-> System.out.print(x + "\t"));
            if (e.getMessage().contains("Too Many")) {
                //Если много запросов то ждем 5 сек
                System.out.println("Много запросов спим 5 сек");
                Thread.sleep(5000);
                getResponse = sendPost(body);
            } else if (e.getMessage().contains("connect timed out")) {
                //Если проблема с таймаутом
                System.out.println("Таймаут ждем 3 минуты");
                Thread.sleep(180000);
                getResponse = sendPost(body);
            } else if (e.getMessage().contains("No subject alternative DNS name")) {
                //Если проблема с таймаутом
                System.out.println("проблемы с ДНС ждем 5 минуты");
                Thread.sleep(300000);
                getResponse = sendPost(body);
            } else {
                throw e;
            }
        }
        //String responseString = RequestUtils.postRequest(url, body, map);
        return getResponse;//(GetResponse) JsonUtils.jsonToObject(responseString, GetResponse.class);
    }

    @Override
    public GetResponse sendPost(List params) {
        return null;
    }


    public String createBodyForPost() {
        return null;
    }
}
