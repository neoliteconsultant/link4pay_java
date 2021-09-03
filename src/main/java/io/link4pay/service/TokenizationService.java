package io.link4pay.service;

import io.link4pay.model.Link4PayResponse;
import io.link4pay.model.Result;
import io.link4pay.model.transaction.TransactionResponse;
import io.link4pay.model.tokenization.TokenizationRequest;
import io.link4pay.util.Configuration;
import io.link4pay.util.Http;

import java.util.HashMap;
import java.util.Map;

public class TokenizationService {
    private Configuration configuration;
    private Http http;

    public TokenizationService(Http http, Configuration configuration) {
        this.configuration = configuration;
        this.http = http;
    }



    /**
     * Save a card.
     * @param tokenizationRequest
     */
    public Result<TransactionResponse> saveCard(TokenizationRequest tokenizationRequest){
        Map<String, String> headers = new HashMap<>();
        headers.put("apiType","saveCard");

        Link4PayResponse result = http.post(configuration.getWithoutHostedPaymentPagePath()+"/api/tokenization", tokenizationRequest, headers);
        return new Result<>(result, TransactionResponse.class);
    }


    /**
     * Verify card.
     * @param tokenizationRequest
     */
    public Result<TransactionResponse> verifyCard(TokenizationRequest tokenizationRequest){
        Map<String, String> headers = new HashMap<>();
        headers.put("apiType","verifyCard");

        Link4PayResponse result = http.post(configuration.getWithoutHostedPaymentPagePath()+"/api/tokenization", tokenizationRequest, headers);
        return new Result<>(result, TransactionResponse.class);
    }


    /**
     * Verify Token.
     * @param tokenizationRequest
     */
    public Result<TransactionResponse> verifyToken(TokenizationRequest tokenizationRequest){
        Map<String, String> headers = new HashMap<>();
        headers.put("apiType","verifyToken");


        Link4PayResponse result = http.post(configuration.getWithoutHostedPaymentPagePath()+"/api/tokenization", tokenizationRequest, headers);
        return new Result<>(result, TransactionResponse.class);
    }



    /**
     * Delete Token.
     * @param tokenizationRequest
     */
    public Result<TransactionResponse> deleteToken(TokenizationRequest tokenizationRequest){
        Map<String, String> headers = new HashMap<>();
        headers.put("apiType","deleteToken");

        Link4PayResponse result = http.post(configuration.getWithoutHostedPaymentPagePath()+"/api/tokenization", tokenizationRequest, headers);
        return new Result<>(result, TransactionResponse.class);
    }



    /**
     * Get a customer token.
     * @param tokenizationRequest
     */
    public Result<TransactionResponse> getCustomerToken(TokenizationRequest tokenizationRequest){
        Map<String, String> headers = new HashMap<>();
        headers.put("apiType","getCustomerTokens");

        Link4PayResponse result = http.post(configuration.getWithoutHostedPaymentPagePath()+"/api/tokenization", tokenizationRequest);
        return new Result<>(result, TransactionResponse.class);
    }


}
