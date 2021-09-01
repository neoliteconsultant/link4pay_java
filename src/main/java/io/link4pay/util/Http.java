package io.link4pay.util;

import io.link4pay.exceptions.*;
import io.link4pay.model.Request;
import io.link4pay.security.AESEncryption2;

import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

public class Http {
    public static final String LINE_FEED = "\r\n";
    private static final Pattern NUMBER_PATTERN = Pattern.compile("<number>(.{6}).+?(.{4})</number>");
    private static final Pattern START_GROUP_PATTERN = Pattern.compile("(^)", Pattern.MULTILINE);
    private static final Pattern CVV_PATTERN = Pattern.compile("<cvv>.+?</cvv>");
    
    
    private volatile SSLSocketFactory sslSocketFactory;

    public enum RequestMethod {
        DELETE, GET, POST, PUT;
    }

    private Configuration configuration;

    public Http(Configuration configuration) {
        this.configuration = configuration;
    }



    public String post(String endpoint, Request request)  {
       String response = null;
        try {
            URL url = new URL(endpoint);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json; utf-8");
            connection.setDoOutput(true);

            throwExceptionIfErrorStatusCode(connection.getResponseCode(), connection.getResponseMessage());

            final String requestJSON = request.toJSON();
            SecretKey key = AESEncryption2.generateKey(128);
            IvParameterSpec ivParameterSpec = AESEncryption2.generateIv();

            String cipherText = AESEncryption2.encrypt(requestJSON, key, ivParameterSpec);
            try(OutputStream os = connection.getOutputStream()) {
                byte[] input = cipherText.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            StringBuffer content = new StringBuffer();
            try(BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String inputLine;

                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
            }

            response = content.toString();
        }catch (Exception ex){
            //ioe.printStackTrace();
            ex.getMessage();
        }

        return response;
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
