package io.link4pay.service;

import io.link4pay.model.Result;
import io.link4pay.model.TransactionRequest;
import io.link4pay.model.TransactionResponse;
import io.link4pay.model.checkout.CheckoutRequest;
import io.link4pay.util.Configuration;
import io.link4pay.util.Http;

public class PayoutService {
    private Configuration configuration;
    private Http http;

    public PayoutService(Http http, Configuration configuration) {
        this.configuration = configuration;
        this.http = http;
    }

    /**
     * Capture a previous authorization
     */
    public Result<TransactionResponse> payout(TransactionRequest transactionRequest){
        String result = http.post(configuration.getWithoutHostedPaymentPagePath()+"/api/cardpayments", transactionRequest);
        return new Result<>(result, TransactionResponse.class);

    }
}
