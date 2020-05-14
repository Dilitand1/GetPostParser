package ru.litvinov.getPostParser.FSSPparser.models.postRequest;


import com.fasterxml.jackson.annotation.*;

public class Request {
    private long type;
    private Params params;

    @JsonProperty("type")
    public long getType() { return type; }
    @JsonProperty("type")
    public void setType(long value) { this.type = value; }

    @JsonProperty("params")
    public Params getParams() { return params; }
    @JsonProperty("params")
    public void setParams(Params value) { this.params = value; }
}