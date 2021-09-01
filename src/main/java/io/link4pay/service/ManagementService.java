package io.link4pay.service;

import io.link4pay.model.Result;
import io.link4pay.model.TransactionRequest;
import io.link4pay.model.TransactionResponse;
import io.link4pay.model.management.ManagementRequest;
import io.link4pay.util.Configuration;
import io.link4pay.util.Http;

public class ManagementService {
    private Configuration configuration;
    private Http http;

    public ManagementService(Http http, Configuration configuration) {
        this.configuration = configuration;
        this.http = http;
    }

    /**
     * Capture a previous authorization
     */
    public Result<TransactionResponse> validateSetup(ManagementRequest managementRequest){
        String result = http.post(configuration.getWithoutHostedPaymentPagePath()+"/api/cardpayments", managementRequest);
        return new Result<>(result, TransactionResponse.class);

    }
}
