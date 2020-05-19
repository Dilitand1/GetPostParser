package ru.litvinov.getPostParser.FSSPparser.models.result;

import java.util.*;
import com.fasterxml.jackson.annotation.*;

public class ErrorResult {
    private long code;
    private String message;
    private Errors errors;

    @JsonProperty("code")
    public long getCode() { return code; }
    @JsonProperty("code")
    public void setCode(long value) { this.code = value; }

    @JsonProperty("message")
    public String getMessage() { return message; }
    @JsonProperty("message")
    public void setMessage(String value) { this.message = value; }

    @JsonProperty("errors")
    public Errors getErrors() { return errors; }
    @JsonProperty("errors")
    public void setErrors(Errors value) { this.errors = value; }
}
