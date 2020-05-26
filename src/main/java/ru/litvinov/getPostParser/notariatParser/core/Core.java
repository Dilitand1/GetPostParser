package ru.litvinov.getPostParser.notariatParser.core;

import ru.litvinov.getPostParser.notariatParser.models.result.GetResult;

import java.util.Map;

public interface Core {

    public String sendPost(String body);
    public void processor();
    public Map getHeaders();
}
