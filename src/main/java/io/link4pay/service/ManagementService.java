package io.link4pay.service;

import io.link4pay.model.Link4PayResponse;
import io.link4pay.model.Result;
import io.link4pay.model.transaction.TransactionResponse;
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
     * Validate a set up.
     *
     * @param managementRequest
     * @return {@link Result}
     */
    public Result<TransactionResponse> validateSetup(ManagementRequest managementRequest){
        Link4PayResponse result = http.post(configuration.getWithoutHostedPaymentPagePath()+"/api/cardpayments", managementRequest);
        return new Result<>(result, TransactionResponse.class);

    }
}
