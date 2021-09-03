package io.link4pay.service;

import io.link4pay.model.Result;
import io.link4pay.model.transaction.TransactionRequest;
import io.link4pay.model.transaction.TransactionResponse;
import io.link4pay.util.Configuration;
import io.link4pay.util.Http;
import io.link4pay.model.Link4PayResponse;

import java.util.HashMap;
import java.util.Map;

public class PayoutService {
    private Configuration configuration;
    private Http http;

    public PayoutService(Http http, Configuration configuration) {
        this.configuration = configuration;
        this.http = http;
    }

    /**
     *
     * @param transactionRequest
     * @return
     */
    public Result<TransactionResponse> tokenizationPayout(TransactionRequest transactionRequest){
        Map<String, String> headers = new HashMap<>();
        headers.put("apiType","tokenPayoutDirect");

        Link4PayResponse result = http.post(configuration.getWithoutHostedPaymentPagePath()+"/api/cardpayments", transactionRequest, headers);
        return new Result<>(result, TransactionResponse.class);

    }

    /**
     *
     * @param transactionRequest
     * @return
     */
    public Result<TransactionResponse> cardPayout(TransactionRequest transactionRequest){
        Map<String, String> headers = new HashMap<>();
        headers.put("apiType","paymentWithoutHpp");

        Link4PayResponse result = http.post(configuration.getWithoutHostedPaymentPagePath()+"/api/cardpayments", transactionRequest, headers);
        return new Result<>(result, TransactionResponse.class);

    }
}
