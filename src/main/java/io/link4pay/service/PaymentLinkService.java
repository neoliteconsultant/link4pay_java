package io.link4pay.service;

import io.link4pay.model.Result;
import io.link4pay.model.TransactionRequest;
import io.link4pay.model.TransactionResponse;
import io.link4pay.util.Configuration;
import io.link4pay.util.Http;

public class PaymentLinkService {
    private Configuration configuration;
    private Http http;

    public PaymentLinkService(Http http, Configuration configuration) {
        this.configuration = configuration;
        this.http = http;
    }

    /**
     * Capture a previous authorization
     */
    public Result<TransactionResponse> generateLink(TransactionRequest transactionRequest){
        String result = http.post("https://hpptest.link4pay.com/paymentpage", transactionRequest);
        return new Result<>(result, TransactionResponse.class);

    }
}
