package io.link4pay.util;

import io.link4pay.model.Request;

import javax.net.ssl.SSLSocketFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
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
        StringBuffer content = new StringBuffer();
        try {
            URL url = new URL(endpoint);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            connection.disconnect();
        }catch (IOException ioe){
            ioe.printStackTrace();
        }

        return content.toString();
    }


}
