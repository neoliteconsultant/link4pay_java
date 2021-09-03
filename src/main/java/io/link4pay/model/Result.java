package io.link4pay.model;

import com.google.gson.Gson;
import java.util.Map;

public class Result<T> {
    private Map<String, String> parameters;
    private String message;
    private T target;
    private Link4PayResponse output;
    private Gson gson = new Gson();
    private int statusCode;


    public Result() {
    }


    public Result(Link4PayResponse output, Class<T> klass) {
        this.output = output;
        if(output.isSuccess()) {
            this.target = gson.fromJson(output.getResponse(), klass);
        }else {
            message = output.getErrorMessage();
            statusCode = output.getStatusCode();
        }

    }

    public Result(T target) {
        this.target = target;
    }



    public Map<String, String> getParameters() {
        return parameters;
    }

    public T getTarget() {
        return target;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public boolean isSuccess() {
        return output.isSuccess();
    }

    public String getMessage() {
        return message;
    }
}
