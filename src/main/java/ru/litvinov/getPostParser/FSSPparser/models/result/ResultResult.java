package ru.litvinov.getPostParser.FSSPparser.models.result;

import java.util.*;
import com.fasterxml.jackson.annotation.*;

public class ResultResult {
    private String name;
    private String exeProduction;
    private String details;
    private String subject;
    private String department;
    private String bailiff;
    private String ipEnd;

    @JsonProperty("name")
    public String getName() { return name; }
    @JsonProperty("name")
    public void setName(String value) { this.name = value; }

    @JsonProperty("exe_production")
    public String getExeProduction() { return exeProduction; }
    @JsonProperty("exe_production")
    public void setExeProduction(String value) { this.exeProduction = value; }

    @JsonProperty("details")
    public String getDetails() { return details; }
    @JsonProperty("details")
    public void setDetails(String value) { this.details = value; }

    @JsonProperty("subject")
    public String getSubject() { return subject; }
    @JsonProperty("subject")
    public void setSubject(String value) { this.subject = value; }

    @JsonProperty("department")
    public String getDepartment() { return department; }
    @JsonProperty("department")
    public void setDepartment(String value) { this.department = value; }

    @JsonProperty("bailiff")
    public String getBailiff() { return bailiff; }
    @JsonProperty("bailiff")
    public void setBailiff(String value) { this.bailiff = value; }

    @JsonProperty("ip_end")
    public String getIPEnd() { return ipEnd; }
    @JsonProperty("ip_end")
    public void setIPEnd(String value) { this.ipEnd = value; }
}