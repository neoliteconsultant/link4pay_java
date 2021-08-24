package io.link4pay.service;

import io.link4pay.model.HostedPayment;
import io.link4pay.model.PaymentRequest;
import io.link4pay.model.Result;
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
    public Result<HostedPayment> payWithHPP(PaymentRequest paymentRequest){
        String result = http.post(configuration.getHostedPaymentPagePath()+"/paymentpage", paymentRequest);
        return new Result<>(result, HostedPayment.class);

    }


    /**
     * Make payment Without Hosted PaymentService Page (HPP).
     */
    public void makePayment(PaymentRequest paymentRequest){}


    /**
     * Capture a previous authorization
     */
    public void capture(PaymentRequest paymentRequest){

    }

    /**
     * Cancel a previous authorization.
     */
    public void voidTransaction(PaymentRequest paymentRequest){

    }


    /**
     * Refund a Transaction.
     */
    public void refundTransaction(PaymentRequest paymentRequest){

    }
}
