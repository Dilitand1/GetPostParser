package ru.litvinov.getPostParser.FSSPparser.models.result;

import java.util.*;
import com.fasterxml.jackson.annotation.*;

public class ResponseResult {
    private long status;
    private Query query;
    private ResultResult[] result;

    @JsonProperty("status")
    public long getStatus() { return status; }
    @JsonProperty("status")
    public void setStatus(long value) { this.status = value; }

    @JsonProperty("query")
    public Query getQuery() { return query; }
    @JsonProperty("query")
    public void setQuery(Query value) { this.query = value; }

    @JsonProperty("result")
    public ResultResult[] getResult() { return result; }
    @JsonProperty("result")
    public void setResult(ResultResult[] value) { this.result = value; }
}
