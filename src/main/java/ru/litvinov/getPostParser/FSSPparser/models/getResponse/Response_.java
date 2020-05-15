package ru.litvinov.getPostParser.FSSPparser.models.getResponse;


import java.io.Serializable;

public class Response_ implements Serializable {
    public String task;

    public Response_(){

    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }
}
