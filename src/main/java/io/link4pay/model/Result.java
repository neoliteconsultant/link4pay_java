package io.link4pay.model;

import com.google.gson.Gson;
import java.util.Map;

public class Result<T> {
    private Map<String, String> parameters;
    private String message;
    private T target;
    private Gson gson = new Gson();


    public Result() {
    }


    public Result(String output, Class<T> klass) {
        this.target = gson.fromJson(output,klass);

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

    public boolean isSuccess() {
        return target == null;
    }

    public String getMessage() {
        return message;
    }
}
