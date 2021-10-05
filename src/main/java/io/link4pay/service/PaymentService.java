package io.link4pay.service;

import io.link4pay.model.checkout.CheckoutRequest;
import io.link4pay.model.transaction.TransactionResponse;
import io.link4pay.model.payment.HostedPayment;
import io.link4pay.model.transaction.TransactionRequest;
import io.link4pay.model.Result;
import io.link4pay.model.refund.RefundRequest;
import io.link4pay.model.void_transaction.VoidRequest;
import io.link4pay.util.Configuration;
import io.link4pay.util.Http;
import io.link4pay.model.Link4PayResponse;

import java.util.HashMap;
import java.util.Map;

public class PaymentService {
    private Configuration configuration;
    private Http http;

    public PaymentService(Http http, Configuration configuration) {
        this.configuration = configuration;
        this.http = http;
    }

    /**
     * Make payment With Hosted PaymentService Page (HPP).
     * @param transactionRequest
     * @return {@link Result}
     */
    public Result<HostedPayment> payWithHPP(TransactionRequest transactionRequest){
        Link4PayResponse result = http.post(configuration.getHostedPaymentPagePath()+"/paymentpage", transactionRequest);
        return new Result<>(result, HostedPayment.class);

    }


    /**
     * Make payment Without Hosted PaymentService Page (HPP).
     *
     * @param transactionRequest
     * @return {@link Result}
     */
    public Result<HostedPayment> makePayment(TransactionRequest transactionRequest){
        Map<String, String> headers = new HashMap<>();
        headers.put("apiType","paymentWithoutHpp");
        Link4PayResponse result = http.post(configuration.getWithoutHostedPaymentPagePath()+"/api/cardpayments", transactionRequest, headers);
        return new Result<>(result, HostedPayment.class);

    }


    /**
     * Capture a previous authorization.
     *
     * @param checkoutRequest
     * @return {@link Result}
     */
    public Result<TransactionResponse> capture(CheckoutRequest checkoutRequest){
        Map<String, String> headers = new HashMap<>();
        headers.put("apiType","capture");
        Link4PayResponse result = http.post(configuration.getWithoutHostedPaymentPagePath()+"/api/cardpayments", checkoutRequest, headers);
        return new Result<>(result, TransactionResponse.class);

    }

    /**
     * Cancel a previous authorization.
     * @param voidRequest
     *
     * @return {@link Result}
     */
    public Result<TransactionResponse> voidTransaction(VoidRequest voidRequest){
        Map<String, String> headers = new HashMap<>();
        headers.put("apiType","void");
        Link4PayResponse result = http.post(configuration.getWithoutHostedPaymentPagePath()+"/api/cardpayments", voidRequest, headers);
        return new Result<>(result, TransactionResponse.class);

    }


    /**
     * Refund a Transaction.
     * @param refundRequest
     *
     * @return {@link Result}
     */
    public Result<TransactionResponse>  refundTransaction(RefundRequest refundRequest){
        Map<String, String> headers = new HashMap<>();
        headers.put("apiType","refund");
        Link4PayResponse result = http.post(configuration.getWithoutHostedPaymentPagePath()+"/api/cardpayments", refundRequest, headers);
        return new Result<>(result, TransactionResponse.class);

    }
}
