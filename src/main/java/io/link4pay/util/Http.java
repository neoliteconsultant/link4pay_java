package io.link4pay.util;

import com.google.gson.Gson;
import io.link4pay.model.Link4PayRequest;
import io.link4pay.model.Link4PayResponse;
import io.link4pay.model.Request;
import io.link4pay.model.transaction.TransactionResponse;
import io.link4pay.security.AESEncryption;
import io.link4pay.security.Certificate;
import io.link4pay.security.ConversionUtil;
import io.link4pay.security.DataEncryption;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;
import java.util.Map;

public class Http {
    private Configuration configuration;
    private Gson gson;

    public Http() {
        gson = new Gson();
    }

    public Http(Configuration configuration) {
        this.configuration = configuration;
        gson = new Gson();
    }


    public Link4PayResponse post(String endpoint, Request request) {
        Link4PayResponse response = post(endpoint, request, null);

        return response;
    }

    public Link4PayResponse post(String endpoint, Request request, Map<String, String> headers) {
        Link4PayResponse link4PayResponse = new Link4PayResponse();
        try {
            URL url = new URL(endpoint);
            System.out.println("======== HTTP ENDPOINT ==================");
            System.out.println(endpoint);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.addRequestProperty("Authorization", authorizationHeader());
            if (headers != null) {
                for (Map.Entry<String, String> entry : headers.entrySet()) {
                    connection.addRequestProperty(entry.getKey(), entry.getValue());
                }
            }
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json; utf-8");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);

            final String requestJSON = request.toJSON();
            IvParameterSpec ivParameterSpec = AESEncryption.generateIv();

            Certificate certificate = new Certificate(new FileInputStream(configuration.getPublicKey()),
                    new FileInputStream(configuration.getPrivateKey()));

            final String fingerprint = certificate.getThumbPrint();
            final String aesKey = fingerprint+configuration.getAccessToken();

            SecretKey key = ConversionUtil.getKeyFromPassword(aesKey, "@$#baelDunG@#^$*");


            String cipherText = AESEncryption.encrypt(requestJSON, key, ivParameterSpec);
            Link4PayRequest link4PayRequest = new Link4PayRequest();
            link4PayRequest.setLang("en");
            link4PayRequest.setPayLoad(cipherText);
            link4PayRequest.setApiKey(configuration.getApiKey());

            final String requestPayload = gson.toJson(link4PayRequest);
            System.out.println("======== HTTP REQUEST ==================");
            System.out.println(requestPayload);
            System.out.println("======== HTTP REQUEST LENGTH ==================");
            System.out.println(cipherText.length());

            System.out.println("======== DECRYPT ==================");
            System.out.println(AESEncryption.decrypt(cipherText,key,ivParameterSpec));
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = requestPayload.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            final int connectionResponseCode = connection.getResponseCode();
            link4PayResponse.setStatusCode(connectionResponseCode);
            if (isErrorCode(connectionResponseCode)) {
                System.out.println("======== ERROR MESSAGE ==================");
                link4PayResponse.setErrorMessage(connection.getResponseMessage());

                /*

                StringBuffer content = new StringBuffer();
                try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                    String inputLine;

                    while ((inputLine = in.readLine()) != null) {
                        content.append(inputLine);
                    }
                }

                String response = content.toString();
                System.out.println(response);
                final TransactionResponse transactionResponse = gson.fromJson(response, TransactionResponse.class);

                link4PayResponse.setStatusCode(transactionResponse.response.responseCode);
                link4PayResponse.setErrorMessage(transactionResponse.response.description);

                System.out.println("=="+transactionResponse.response.description);
                */

            } else {
                StringBuffer content = new StringBuffer();
                try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                    String inputLine;

                    while ((inputLine = in.readLine()) != null) {
                        content.append(inputLine);
                    }
                }

                System.out.println("======== HTTP RESPONSE ==================");
                String response = content.toString();
                System.out.println(response);
                link4PayResponse.setResponse(response);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            ex.getMessage();
        }

        return link4PayResponse;
    }

    public String authorizationHeader() {
        String credentials = configuration.getPublicKey() + ":" + configuration.getPrivateKey();

        return "Basic " + Base64.getEncoder().encodeToString(credentials.getBytes());
    }


    private static boolean isErrorCode(int responseCode) {
        return responseCode != 200 && responseCode != 201 && responseCode != 422;
    }


}
