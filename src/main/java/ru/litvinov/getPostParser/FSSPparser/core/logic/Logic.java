package ru.litvinov.getPostParser.FSSPparser.core.logic;

import java.util.List;

public interface Logic {
    public Object sendGet(String url, String token) throws Exception;

    public Object takeResult(String task, String token) throws Exception;

    public Object sendPost(String body) throws Exception;

    public Object sendPost(List params);
}
