package ru.litvinov.getPostParser.utils.jsonUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

//для успешного преобразования нужно Pojo - геттеры, сеттеры и конструктор по умолячанию
public class JsonUtils {

    public static String objectToJson(Object obj){
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e){
            e.printStackTrace();
        }
        return null;
    }

    public static Object jsonToObject(String json, Class myClass){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Object o = objectMapper.readValue(json,myClass);
            return o;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
