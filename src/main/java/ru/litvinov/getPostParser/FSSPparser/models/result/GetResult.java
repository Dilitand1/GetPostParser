package ru.litvinov.getPostParser.FSSPparser.models.result;

import java.util.*;
import com.fasterxml.jackson.annotation.*;

public class GetResult {
    private String status;
    private long code;
    private String exception;
    private Response response;

    @JsonProperty("status")
    public String getStatus() { return status; }
    @JsonProperty("status")
    public void setStatus(String value) { this.status = value; }

    @JsonProperty("code")
    public long getCode() { return code; }
    @JsonProperty("code")
    public void setCode(long value) { this.code = value; }

    @JsonProperty("exception")
    public String getException() { return exception; }
    @JsonProperty("exception")
    public void setException(String value) { this.exception = value; }

    @JsonProperty("response")
    public Response getResponse() { return response; }
    @JsonProperty("response")
    public void setResponse(Response value) { this.response = value; }
}
