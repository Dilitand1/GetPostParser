package ru.litvinov.getPostParser.FSSPparser.core;

import java.util.List;

public interface Logic {
    public Object sendGet(String url) throws Exception;
    public Object takeResult(String task) throws Exception;
    public Object sendPost(String body) throws Exception;
    public Object sendPost(List params);
}
