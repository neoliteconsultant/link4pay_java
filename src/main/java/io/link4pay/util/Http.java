package io.link4pay.util;

import io.link4pay.exceptions.*;
import io.link4pay.model.Link4PayResponse;
import io.link4pay.model.Request;
import io.link4pay.security.AESEncryption;

import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Base64;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Http {
    private Configuration configuration;

    public Http(Configuration configuration) {
        this.configuration = configuration;
    }


    public Link4PayResponse post(String endpoint, Request request) {
        Link4PayResponse response = post(endpoint, request, null);

        return response;
    }

    public Link4PayResponse post(String endpoint, Request request, Map<String, String> headers) {
        Link4PayResponse link4PayResponse = new Link4PayResponse();
        String response = null;
        try {
            URL url = new URL(endpoint);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.addRequestProperty("Authorization", authorizationHeader());
            if (headers != null) {
                for (Map.Entry<String, String> entry : headers.entrySet()) {
                    connection.addRequestProperty(entry.getKey(), entry.getValue());
                }
            }
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json; utf-8");
            connection.setDoOutput(true);

            final int connectionResponseCode = connection.getResponseCode();

            link4PayResponse.setStatusCode(connectionResponseCode);
            if (isErrorCode(connectionResponseCode)) {
                link4PayResponse.setErrorMessage(connection.getResponseMessage());


            } else {
                final String requestJSON = request.toJSON();
                SecretKey key = AESEncryption.generateKey(128);
                IvParameterSpec ivParameterSpec = AESEncryption.generateIv();

                String cipherText = AESEncryption.encrypt(requestJSON, key, ivParameterSpec);
                try (OutputStream os = connection.getOutputStream()) {
                    byte[] input = cipherText.getBytes("utf-8");
                    os.write(input, 0, input.length);
                }

                StringBuffer content = new StringBuffer();
                try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                    String inputLine;

                    while ((inputLine = in.readLine()) != null) {
                        content.append(inputLine);
                    }
                }

                response = content.toString();
                link4PayResponse.setResponse(response);
            }

        } catch (Exception ex) {
            //ioe.printStackTrace();
            ex.getMessage();
        }

        return link4PayResponse;
    }

    public String authorizationHeader() {
        if (configuration.isAccessToken()) {
            return "Bearer " + configuration.getAccessToken();
        }
        String credentials;
        if (configuration.isClientCredentials()) {
            credentials = configuration.getClientId() + ":" + configuration.getClientSecret();
        } else {
            credentials = configuration.getPublicKey() + ":" + configuration.getPrivateKey();
        }
        return "Basic " + Base64.getEncoder().encodeToString(credentials.getBytes());
    }


    public static void throwExceptionIfErrorStatusCode(int statusCode, String message) {
        if (isErrorCode(statusCode)) {
            String decodedMessage = null;
            if (message != null) {
                try {
                    decodedMessage = URLDecoder.decode(message, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    Logger logger = Logger.getLogger("Link4Pay");
                    logger.log(Level.FINEST, e.getMessage(), e.getStackTrace());
                }
            }

            switch (statusCode) {
                case 401:
                    throw new AuthenticationException();
                case 403:
                    throw new AuthorizationException(decodedMessage);
                case 404:
                    throw new NotFoundException();
                case 408:
                    throw new RequestTimeoutException();
                case 426:
                    throw new UpgradeRequiredException();
                case 429:
                    throw new TooManyRequestsException();
                case 500:
                    throw new ServerException();
                case 503:
                    throw new ServiceUnavailableException();
                case 504:
                    throw new GatewayTimeoutException();
                default:
                    throw new UnexpectedException("Unexpected HTTP_RESPONSE " + statusCode);

            }
        }
    }

    private static boolean isErrorCode(int responseCode) {
        return responseCode != 200 && responseCode != 201 && responseCode != 422;
    }


}
