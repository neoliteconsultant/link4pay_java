package io.link4pay.util;

import java.util.Arrays;

/**
 * Indicates the environment of the Link4pay Gateway with which to interact.
 */
public class Environment {
    /** For Link4pay internal development. */
    public static final Environment DEVELOPMENT = new Environment(developmentBaseURL() + ":" + developmentPort(), "http://auth.venmo.dev:9292", new String[]{}, "development");
    public static final Environment QA = new Environment("https://gateway.qa.braintreepayments.com:443", "https://auth.qa.venmo.com", new String[]{"ssl/api_link4pay_com.ca.crt", "ssl/payments_braintreeapi_com.ca.crt"}, "qa");

    /** For production. */
    public static final Environment PRODUCTION = new Environment("https://api.link4pay.com:443", "https://auth.venmo.com", new String[]{"ssl/api_link4pay_com.ca.crt", "ssl/payments_braintreeapi_com.ca.crt"}, "production");

    /** For merchants to use during their development and testing. */
    public static final Environment SANDBOX = new Environment("https://api.sandbox.link4pay.com:443", "https://auth.sandbox.venmo.com", new String[]{"ssl/api_link4pay_com.ca.crt", "ssl/payments_braintreeapi_com.ca.crt"}, "sandbox");

    private String environmentName;

    public final String baseURL;
    public final String authURL;
    public final String[] certificateFilenames;


    public Environment(String baseURL, String authURL, String[] certificateFilenames, String environmentName) {
        this.baseURL = baseURL;
        this.authURL = authURL;
        this.certificateFilenames = Arrays.copyOf(certificateFilenames, certificateFilenames.length);
        this.environmentName = environmentName;
    }

    public static Environment parseEnvironment(String environment) {
        if (environment.equals("development") || environment.equals("integration")) {
            return DEVELOPMENT;
        } else if (environment.equals("qa")) {
            return QA;
        } else if (environment.equals("sandbox")) {
            return SANDBOX;
        } else if (environment.equals("production")) {
            return PRODUCTION;
        } else {
            throw new IllegalArgumentException("Unknown environment: " + environment);
        }
    }


    private static String developmentBaseURL() {
        if (System.getenv().get("GATEWAY_BASE_URL") != null) {
            return System.getenv().get("GATEWAY_BASE_URL");
        } else {
            return "http://localhost";
        }
    }

    public static String developmentPort() {
        if (System.getenv().get("GATEWAY_PORT") != null) {
            return System.getenv().get("GATEWAY_PORT");
        } else {
            return "3000";
        }
    }

    public String getEnvironmentName() {
        return this.environmentName;
    }

    public String toString() {
        return getEnvironmentName();
    }
}
