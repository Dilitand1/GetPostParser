package ru.litvinov.getPostParser.FSSPparser.models.postRequest;

import com.fasterxml.jackson.annotation.*;

import java.util.List;

public class PostRequest {
    private String token;
    private List<Request> request;

    @JsonProperty("token")
    public String getToken() { return token; }
    @JsonProperty("token")
    public void setToken(String value) { this.token = value; }

    @JsonProperty("request")
    public List<Request> getRequest() { return request; }
    @JsonProperty("request")
    public void setRequest(List<Request> value) { this.request = value; }
}