package io.link4pay;

import io.link4pay.service.PaymentService;
import io.link4pay.util.Configuration;
import io.link4pay.util.Http;

public class Link4PayGateway {
    private Configuration configuration;
    private Http http;

    /**
     * Instantiates a Link4Pay. Use the values provided by Braintree.
     *
     * @param environment
     *            Either {@link Environment#SANDBOX} or
     *            {@link Environment#PRODUCTION}.
     * @param merchantId
     *            the merchant id provided by Link4Pay.
     * @param publicKey
     *            the public key provided by Link4Pay.
     * @param privateKey
     *            the private key provided by Link4Pay.
     */
    public Link4PayGateway(Environment environment, String merchantId, String publicKey, String privateKey) {
        //this.configuration = new Configuration(environment, merchantId, publicKey, privateKey);
        this.http = new Http(configuration);
    }

    public Link4PayGateway(String environment, String merchantId, String publicKey, String privateKey) {
        this.configuration = new Configuration(environment, merchantId, publicKey, privateKey);
        this.http = new Http(configuration);
    }

    public Link4PayGateway(String clientId, String clientSecret) {
        this.configuration = new Configuration(clientId, clientSecret);
        this.http = new Http(configuration);

    }

    public Link4PayGateway(String accessToken) {
        this.configuration = new Configuration(accessToken);
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
}