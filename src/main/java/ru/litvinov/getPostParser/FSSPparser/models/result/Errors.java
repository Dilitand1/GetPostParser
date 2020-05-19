package ru.litvinov.getPostParser.FSSPparser.models.result;

import java.util.*;
import com.fasterxml.jackson.annotation.*;

public class Errors {
    private String[] number;

    @JsonProperty("number")
    public String[] getNumber() { return number; }
    @JsonProperty("number")
    public void setNumber(String[] value) { this.number = value; }
}