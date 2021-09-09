package io.link4pay;

import io.link4pay.service.*;
import io.link4pay.util.Configuration;
import io.link4pay.util.Http;

public class Link4PayGateway {
    private Configuration configuration;
    private Http http;

    /**
     * Instantiates a Link4Pay.
     *
     * @param environment
     *            Either {@link Environment#SANDBOX} or
     *            {@link Environment#PRODUCTION}.
     * @param apiKey
     *            the API key provided by Link4Pay.
     * @param publicKey
     *            the public key provided by Link4Pay.
     * @param privateKey
     *            the private key provided by Link4Pay.
     */
    public Link4PayGateway(Environment environment, String apiKey, String publicKey, String privateKey) {
        //this.configuration = new Configuration(environment, merchantId, publicKey, privateKey);
        this.http = new Http(configuration);
    }


    /**
     * Instantiates a Link4Pay.
     *
     * @param environment
     *            Either sandbox or production.
     * @param apiKey
     *            the API key provided by Link4Pay.
     * @param publicKey
     *            the public key provided by Link4Pay.
     * @param privateKey
     *            the private key provided by Link4Pay.
     */

    public Link4PayGateway(String environment, String apiKey, String publicKey, String privateKey) {
        this.configuration = new Configuration(environment, apiKey, publicKey, privateKey);
        this.http = new Http(configuration);
    }



    /**
     * Returns an {@link PaymentService} service for interacting with
     * PaymentService API.
     *
     * @return an {@link PaymentService}.
     */
    public PaymentService paymentService() {
        return new PaymentService(http, configuration);
    }

    /**
     * Returns an {@link PayoutService} service for interacting with
     * PayoutService API.
     *
     * @return an {@link PayoutService}.
     */
    public PayoutService payout() {
        return new PayoutService(http, configuration);
    }

    /**
     * Returns an {@link TokenizationService} service for interacting with
     * PaymentService API.
     *
     * @return an {@link TokenizationService}.
     */
    public TokenizationService tokenization() {
        return new TokenizationService(http, configuration);
    }


    /**
     * Returns an {@link PaymentService} service for interacting with
     * PaymentService API.
     *
     * @return an {@link PaymentService}.
     */
    public PaymentLinkService paymentLink() {
        return new PaymentLinkService(http, configuration);
    }

    public ManagementService managementService() {
        return new ManagementService(http, configuration);
    }
}
