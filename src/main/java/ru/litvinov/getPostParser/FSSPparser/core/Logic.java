package ru.litvinov.getPostParser.FSSPparser.core;

public interface Logic {
    public Object sendGet(String url);
    public Object takeResult(String task);
    public Object sendPost(String url, String body);
}
