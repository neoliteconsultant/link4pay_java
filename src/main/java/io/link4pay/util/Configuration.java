package io.link4pay.util;


import io.link4pay.exceptions.ConfigurationException;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Configuration {
    private Environment environment;
    private int timeout;
    private int connectTimeout;
    private Proxy proxy;
    private String accessToken;
    private String clientId;
    private String clientSecret;
    private String privateKey;
    private String publicKey;
    private static Logger logger;

    static {
        logger = Logger.getLogger("Link4Pay");
        logger.setLevel(Level.INFO);
    }




    public Configuration(Environment environment,String publicKey, String privateKey) {
        this.environment = environment;



        if (publicKey == null || publicKey.isEmpty()) {
            throw new ConfigurationException("publicKey needs to be set");
        } else {
            this.publicKey = publicKey;
        }

        if (privateKey == null || privateKey.isEmpty()) {
            throw new ConfigurationException("privateKey needs to be set");
        } else {
            this.privateKey = privateKey;
        }
    }

    public Configuration(String environment, String publicKey, String privateKey) {
        this(Environment.parseEnvironment(environment),  publicKey, privateKey);
    }



    public Configuration(String clientId, String clientSecret) {
        /*
        CredentialsParser parser = new CredentialsParser(clientId, clientSecret);
        this.environment = parser.environment;
        this.clientId = parser.clientId;
        this.clientSecret = parser.clientSecret;*/
    }

    public Configuration(String accessToken) {
        /*
        CredentialsParser parser = new CredentialsParser(accessToken);
        this.environment = parser.environment;
        this.merchantId = parser.merchantId;
        this.accessToken = parser.accessToken;*/
    }

    public Environment getEnvironment() {
        return environment;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public String getClientId() {
        return clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public Boolean isClientCredentials() {
        return clientId != null;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public Boolean isAccessToken() {
        return accessToken != null;
    }

    public String getHostedPaymentPagePath() {
        return "https://hpptest.link4pay.com";
    }

    public String getWithoutHostedPaymentPagePath() {
        return "https://merchant.test.link4pay.com";
    }

    public String getBaseURL() {
        return environment.baseURL;
    }

    public Boolean usesProxy() {
        return proxy != null;
    }

    public void setProxy(String url, Integer port) {
        this.proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(url, port));
    }

    public void setProxy(Proxy proxy) {
        this.proxy = proxy;
    }

    public Proxy getProxy() {
        return proxy;
    }

    public Logger getLogger() {
        return logger;
    }

    public void setLogger(Logger log) {
        logger = log;
    }

    public int getTimeout() {
        return (timeout == 0) ? 60000 : timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    public int getConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout(Integer timeout) {
        this.connectTimeout = timeout;
    }
}
