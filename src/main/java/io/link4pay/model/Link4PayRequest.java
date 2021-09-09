package io.link4pay.model;

public class Link4PayRequest {
    private String apiKey;
    private String lang;
    private String payLoad;

    public String getApiKey() {
        return this.apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }


    public String getLang() {
        return this.lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getPayLoad() {
        return this.payLoad;
    }

    public void setPayLoad(String payLoad) {
        this.payLoad = payLoad;
    }


}
