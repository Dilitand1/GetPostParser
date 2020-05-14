package ru.litvinov.getPostParser.FSSPparser.core;

import java.util.List;

public interface Logic {
    public Object sendGet(String url);
    public Object takeResult(String task);
    public Object sendPost(String body);
    public Object sendPost(List params);
}
