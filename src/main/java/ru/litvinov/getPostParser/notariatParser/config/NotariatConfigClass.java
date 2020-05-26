package ru.litvinov.getPostParser.notariatParser.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
@ComponentScans(@ComponentScan("ru.litvinov.getPostParser.notariatParser"))
@PropertySource("notariat.properties")
public class NotariatConfigClass {

    @Autowired
    private Environment environment;

    @Bean(name = "headers")
    public Map<String,String> headersBean(){

        Map<String,String> map = new LinkedHashMap<>();
        map.put("Host","notariat.ru");
        map.put("Connection","keep-alive");
        map.put("Content-Length","89");//длина пост запроса
        map.put("Accept","application/json, text/javascript, */*; q=0.01");
        //map.put("X-CSRFToken","ApMvn8liRxVhlwUdzXgWxJ9hU0Hojory");//Генерится из куки
        map.put("X-CSRFToken",environment.getProperty("notariat.X-CSRFToken"));//Генерится из куки
        map.put("X-Requested-With","XMLHttpRequest");
        map.put("Save-Data","on");
        map.put("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/81.0.4044.138 YaBrowser/20.4.2.197 Yowser/2.5 Yptp/1.21 Safari/537.36");
        map.put("Content-Type","application/json; charset=UTF-8");
        map.put("Origin","https://notariat.ru");
        map.put("Sec-Fetch-Site", "same-origin");
        map.put("Sec-Fetch-Mode", "cors");
        map.put("Sec-Fetch-Dest","empty");
        map.put("Referer","https://notariat.ru/ru-ru/help/probate-cases/");
        //map.put("Accept-Encoding","gzip, deflate, br");//сжатие - надо разбираться как обрабатывать такое
        map.put("Accept-Language", "ru,en;q=0.9");
        map.put("Cookie",environment.getProperty("notariat.Cookie"));//куки получим перед входом
        //map.put("Cookie","fnc_csrftoken=ApMvn8liRxVhlwUdzXgWxJ9hU0Hojory; django_language=ru-ru");
        return map;
    }
}
