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
        map.put("User-Agent","Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:76.0) Gecko/20100101 Firefox/76.0");
        map.put("Accept","application/json, text/javascript, */*; q=0.01");
        map.put("Accept-Language","ru,en;q=0.9");
        map.put("Accept-Encoding","gzip, deflate, br");
        map.put("Content-Type","application/json; charset=utf-8");
        map.put("X-CSRFToken","ApMvn8liRxVhlwUdzXgWxJ9hU0Hojory");//Генерится из куки
        map.put("X-Requested-With","XMLHttpRequest");
        map.put("Content-Length","");//длина пост запроса
        map.put("Origin","https://notariat.ru");
        map.put("Connection","keep-alive");
        map.put("Referer","https://notariat.ru/ru-ru/help/probate-cases/");
        map.put("Cookie","fnc_csrftoken=ApMvn8liRxVhlwUdzXgWxJ9hU0Hojory; django_language=ru-ru; _ym_d=1589972369; _ym_uid=1589972369132099291; _ga=GA1.2.1715505306.1589972369; _gid=GA1.2.195929137.1590389132; _ym_isad=2; _gat_gtag_UA_109330537_1=1; _ym_visorc_16425640=w");//куки получим перед входом
        return map;

    }
}
