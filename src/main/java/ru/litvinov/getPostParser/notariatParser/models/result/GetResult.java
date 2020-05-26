package ru.litvinov.getPostParser.notariatParser.models.result;

import java.util.*;

import com.fasterxml.jackson.annotation.*;

public class GetResult {
    private long count;
    private Record[] records;

    @JsonProperty("count")
    public long getCount() {
        return count;
    }

    @JsonProperty("count")
    public void setCount(long value) {
        this.count = value;
    }

    @JsonProperty("records")
    public Record[] getRecords() {
        return records;
    }

    @JsonProperty("records")
    public void setRecords(Record[] value) {
        this.records = value;
    }
}
