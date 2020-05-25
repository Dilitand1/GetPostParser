package ru.litvinov.getPostParser.notariatParser.core;

import java.util.Map;

public interface Core {

    public String sendPost(String body);
    public String processor();
    public Map getHeaders();
}
