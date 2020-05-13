package ru.litvinov.getPostParser.FSSPparser.models.result;

import java.util.*;
import com.fasterxml.jackson.annotation.*;

public class Response {
    private long status;
    private String taskStart;
    private String taskEnd;
    private ResponseResult[] result;

    @JsonProperty("status")
    public long getStatus() { return status; }
    @JsonProperty("status")
    public void setStatus(long value) { this.status = value; }

    @JsonProperty("task_start")
    public String getTaskStart() { return taskStart; }
    @JsonProperty("task_start")
    public void setTaskStart(String value) { this.taskStart = value; }

    @JsonProperty("task_end")
    public String getTaskEnd() { return taskEnd; }
    @JsonProperty("task_end")
    public void setTaskEnd(String value) { this.taskEnd = value; }

    @JsonProperty("result")
    public ResponseResult[] getResult() { return result; }
    @JsonProperty("result")
    public void setResult(ResponseResult[] value) { this.result = value; }
}