package io.link4pay.service;

import io.link4pay.model.checkout.CheckoutRequest;
import io.link4pay.model.TransactionResponse;
import io.link4pay.model.payment.HostedPayment;
import io.link4pay.model.TransactionRequest;
import io.link4pay.model.Result;
import io.link4pay.model.refund.RefundRequest;
import io.link4pay.model.void_transaction.VoidRequest;
import io.link4pay.util.Configuration;
import io.link4pay.util.Http;

public class PaymentService {
    private Configuration configuration;
    private Http http;

    public PaymentService(Http http, Configuration configuration) {
        this.configuration = configuration;
        this.http = http;
    }

    /**
     * Make payment With Hosted PaymentService Page (HPP).
     */
    public Result<HostedPayment> payWithHPP(TransactionRequest transactionRequest){
        String result = http.post(configuration.getHostedPaymentPagePath()+"/paymentpage", transactionRequest);
        return new Result<>(result, HostedPayment.class);

    }


    /**
     * Make payment Without Hosted PaymentService Page (HPP).
     */
    public Result<HostedPayment> makePayment(TransactionRequest transactionRequest){
        String result = http.post(configuration.getWithoutHostedPaymentPagePath()+"/api/cardpayments", transactionRequest);
        return new Result<>(result, HostedPayment.class);

    }


    /**
     * Capture a previous authorization
     */
    public Result<TransactionResponse> capture(CheckoutRequest checkoutRequest){
        String result = http.post(configuration.getWithoutHostedPaymentPagePath()+"/api/cardpayments", checkoutRequest);
        return new Result<>(result, TransactionResponse.class);

    }

    /**
     * Cancel a previous authorization.
     */
    public Result<TransactionResponse> voidTransaction(VoidRequest voidRequest){
        String result = http.post(configuration.getWithoutHostedPaymentPagePath()+"/api/cardpayments", voidRequest);
        return new Result<>(result, TransactionResponse.class);

    }


    /**
     * Refund a Transaction.
     */
    public Result<TransactionResponse>  refundTransaction(RefundRequest refundRequest){
        String result = http.post(configuration.getWithoutHostedPaymentPagePath()+"/api/cardpayments", refundRequest);
        return new Result<>(result, TransactionResponse.class);

    }
}
