package ru.litvinov.getPostParser.FSSPparser.models.getResponse;

public class GetResponse {
    private String status;
    private int code;
    private String exception;
    private Response_ response;

    public GetResponse() {

    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public Response_ getResponse() {
        return response;
    }

    public void setResponse(Response_ response) {
        this.response = response;
    }

    @Override
    public String toString() {
        return //"GetResponse{" +
                response.task + "~" + status + "~" + code + "~" + exception + "_";
    }
}


/*
{
  "status": "success",
  "code": 0,
  "exception": "",
  "response": {
    "task": "7c65f10b-51a5-40a3-8f5a-792566fb0589"
  }
}
 */
