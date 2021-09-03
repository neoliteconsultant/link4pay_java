package io.link4pay.model;

public class Link4PayResponse {
    private String response;
    private String errorMessage;
    private int statusCode;


    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public boolean isSuccess() {
        return statusCode == 200 || statusCode == 201 || statusCode == 422;
    }


}
