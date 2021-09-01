package io.link4pay.model.management;

import com.google.gson.Gson;
import io.link4pay.model.Request;

public class ManagementRequest extends Request {
    private Gson gson = new Gson();
    private String merchantID;
    private String payoutEndpoint;
    private String tokenEndpoint;
    private String certificate;
    private String apiToken;

    public ManagementRequest merchantID(String merchantID) {
        this.merchantID = merchantID;
        return this;
    }

    public ManagementRequest payoutEndpoint(String payoutEndpoint) {
        this.payoutEndpoint = payoutEndpoint;
        return this;
    }

    public ManagementRequest tokenEndpoint(String tokenEndpoint) {
        this.tokenEndpoint = tokenEndpoint;
        return this;
    }

    public ManagementRequest certificate(String certificate) {
        this.certificate = certificate;
        return this;
    }

    public ManagementRequest apiToken(String apiToken) {
        this.apiToken = apiToken;
        return this;
    }

    @Override
    public String toJSON() {
        ManagementHttpRequest managementHttpRequest = new ManagementHttpRequest();
        managementHttpRequest.merchantID = merchantID;
        managementHttpRequest.payoutEndpoint = payoutEndpoint;
        managementHttpRequest.tokenEndpoint = tokenEndpoint;
        managementHttpRequest.certificate = certificate;
        managementHttpRequest.apiToken = apiToken;
        return gson.toJson(managementHttpRequest);
    }
}
