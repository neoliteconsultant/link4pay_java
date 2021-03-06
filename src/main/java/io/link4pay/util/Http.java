package io.link4pay.util;

import com.google.gson.Gson;
import io.link4pay.model.Link4PayRequest;
import io.link4pay.model.Link4PayResponse;
import io.link4pay.model.Request;
import io.link4pay.security.encryption.Certificate;
import io.link4pay.security.encryption.DataEncryption;

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

                    //System.out.println(entry.getKey()+":"+entry.getValue());
                }
            }
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json; utf-8");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);

            final String requestJSON = request.toJSON();

            Certificate certificate = new Certificate(new FileInputStream(configuration.getPublicKey()),
                    new FileInputStream(configuration.getPrivateKey()));
            certificate.getCertificateDetails();

            final String fingerprint = certificate.getThumbPrint();
            final byte[] thumbBytes = fingerprint.getBytes();
            final int thumbLength = thumbBytes.length;

            final String accessToken= configuration.getAccessToken();
            final byte[] accessTokenBytes = accessToken.getBytes();
            final StringBuilder key = new StringBuilder();
            for (int i = 0; i < accessToken.length(); ++i) {
                key.append((char)(accessTokenBytes[i] | thumbBytes[i % thumbLength]));
            }

            DataEncryption dataEncryption = new DataEncryption();
            String cipherText = dataEncryption.encryptText(requestJSON, key.toString());
            Link4PayRequest link4PayRequest = new Link4PayRequest();
            link4PayRequest.setLang("en");
            link4PayRequest.setPayLoad(cipherText);
            link4PayRequest.setApiKey(configuration.getApiKey());

            final String requestPayload = gson.toJson(link4PayRequest);
            System.out.println("======== HTTP REQUEST ==================");
            System.out.println(requestPayload);

            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = requestPayload.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            final int connectionResponseCode = connection.getResponseCode();
            System.out.println("Response Code " +connectionResponseCode);
            link4PayResponse.setStatusCode(connectionResponseCode);
            if (isErrorCode(connectionResponseCode)) {
                System.out.println("======== ERROR MESSAGE ==================");
                link4PayResponse.setErrorMessage(connection.getResponseMessage());



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
