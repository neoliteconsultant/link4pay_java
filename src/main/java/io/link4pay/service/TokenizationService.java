package io.link4pay.service;

import io.link4pay.model.Result;
import io.link4pay.model.TransactionRequest;
import io.link4pay.model.TransactionResponse;
import io.link4pay.model.tokenization.TokenizationRequest;
import io.link4pay.util.Configuration;
import io.link4pay.util.Http;

public class TokenizationService {
    private Configuration configuration;
    private Http http;

    public TokenizationService(Http http, Configuration configuration) {
        this.configuration = configuration;
        this.http = http;
    }



    /**
     * Save a card.
     */
    public Result<TransactionResponse> saveCard(TokenizationRequest tokenizationRequest){
        String result = http.post(configuration.getWithoutHostedPaymentPagePath()+"/api/tokenization", tokenizationRequest);
        return new Result<>(result, TransactionResponse.class);
    }


    /**
     * Verify card.
     */
    public Result<TransactionResponse> verifyCard(TokenizationRequest tokenizationRequest){
        String result = http.post(configuration.getWithoutHostedPaymentPagePath()+"/api/tokenization", tokenizationRequest);
        return new Result<>(result, TransactionResponse.class);
    }


    /**
     * Verify Token.
     */
    public Result<TransactionResponse> verifyToken(TokenizationRequest tokenizationRequest){
        String result = http.post(configuration.getWithoutHostedPaymentPagePath()+"/api/tokenization", tokenizationRequest);
        return new Result<>(result, TransactionResponse.class);
    }



    /**
     * Delete Token.
     */
    public Result<TransactionResponse> deleteToken(TokenizationRequest tokenizationRequest){
        String result = http.post(configuration.getWithoutHostedPaymentPagePath()+"/api/tokenization", tokenizationRequest);
        return new Result<>(result, TransactionResponse.class);
    }



    /**
     * Get a customer token.
     */
    public Result<TransactionResponse> getCustomerToken(TokenizationRequest tokenizationRequest){
        String result = http.post(configuration.getWithoutHostedPaymentPagePath()+"/api/tokenization", tokenizationRequest);
        return new Result<>(result, TransactionResponse.class);
    }


}
