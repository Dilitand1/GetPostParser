package ru.litvinov.getPostParser.notariatParser.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
@ComponentScans(@ComponentScan("ru.litvinov.getPostParser.notariatParser"))
public class NotariatConfigClass {

    @Bean(name = "headers")
    public Map<String,String> headersBean(){
        Map<String,String> map = new LinkedHashMap<>();
        map.put("Host","notariat.ru");
        map.put("Connection","keep-alive");
        map.put("Content-Length","101");//длина пост запроса
        map.put("Accept","application/json, text/javascript, */*; q=0.01");
        map.put("X-CSRFToken","ApMvn8liRxVhlwUdzXgWxJ9hU0Hojory");//Генерится из куки
        map.put("X-Requested-With","XMLHttpRequest");
        map.put("Save-Data","on");
        map.put("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/81.0.4044.138 YaBrowser/20.4.2.197 Yowser/2.5 Yptp/1.21 Safari/537.36");
        map.put("Content-Type","application/json; charset=UTF-8");
        map.put("Origin","https://notariat.ru");
        map.put("Sec-Fetch-Site", "same-origin");
        map.put("Sec-Fetch-Mode", "cors");
        map.put("Sec-Fetch-Dest","empty");
        map.put("Referer","https://notariat.ru/ru-ru/help/probate-cases/");
        map.put("Accept-Encoding","gzip, deflate, br");
        map.put("Accept-Language", "ru,en;q=0.9");
        map.put("Cookie","fnc_csrftoken=ApMvn8liRxVhlwUdzXgWxJ9hU0Hojory; django_language=ru-ru; _ym_d=1589972369; _ym_uid=1589972369132099291; _ga=GA1.2.1715505306.1589972369; _gid=GA1.2.195929137.1590389132; _ym_isad=2; _gat_gtag_UA_109330537_1=1; _ym_visorc_16425640=w");//куки получим перед входом
        return map;

    }
}
