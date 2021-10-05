

package io.link4pay.security.encryption;


import com.google.gson.Gson;

import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.security.Key;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.Mac;
import java.util.TimeZone;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class EncryptionUtils
{
    static Logger log;
    protected String accessToken;
    protected Certificate certificate;
    private Gson gson = new Gson();
    
    public EncryptionUtils(final String accessToken, final Certificate certificate) {
        this.accessToken = accessToken;
        this.certificate = certificate;
    }
    
    public static Map<String, Object> getAuthorizationHeader(final Map<String, Object> dataMap) {
        final String reference = dataMap.get("txnReference").toString();
        final String token = dataMap.get("accessToken").toString();
        final String apiType = dataMap.get("apiType").toString();
        final String merchantId = dataMap.get("apiKey").toString();
        final Map<String, Object> json = new HashMap<String, Object>();
        final Date now = new Date();
        final SimpleDateFormat gmtTimeZone = new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss 'GMT'", Locale.US);
        gmtTimeZone.setTimeZone(TimeZone.getTimeZone("GMT"));
        final SimpleDateFormat istTimeZone = new SimpleDateFormat("HHmmssddMMyyyy", Locale.US);
        istTimeZone.setTimeZone(TimeZone.getTimeZone("Asia/Calcutta"));
        final String encrypt = apiType + "+/pArTnErApI+" + istTimeZone.format(now) + "+" + reference;
        final String hash = encodeHmac(token, encrypt);
        final String finalMacData = "hmac " + merchantId + ":" + reference + ":" + hash;
        json.put("dateHeader", gmtTimeZone.format(now));
        json.put("Authorization", finalMacData);
        return json;
    }

    private static String encodeHmac(final String key, final String data) {
        String hmac = null;
        try {
            final Mac sha256Hmac = Mac.getInstance("HmacSHA256");
            final SecretKeySpec secretkey = new SecretKeySpec(key.getBytes(), "HmacSHA256");
            sha256Hmac.init(secretkey);
            hmac = Base64.getEncoder().encodeToString(sha256Hmac.doFinal(data.getBytes(StandardCharsets.UTF_8)));
        }
        catch (NoSuchAlgorithmException | InvalidKeyException ex2) {
            final GeneralSecurityException ex;
            EncryptionUtils.log.severe(ex2.getMessage());
        }
        return hmac;
    }

    public String getEncryptedData(final Map<String, Object> dataMap, final boolean isAsyncCall, final String lang) {
        final DataEncryption encrypt = new DataEncryption();
        String encryptedText = "";
        final Map<String, Object> payLoadData = new HashMap<String, Object>();
        try {
            this.certificate.getCertificateDetails();
            final byte[] thumbBytes = this.certificate.getThumbPrint().getBytes();
            final int thumbLength = thumbBytes.length;
            final byte[] accessTokenBytes = this.accessToken.getBytes();
            final StringBuilder key = new StringBuilder();
            for (int i = 0; i < this.accessToken.length(); ++i) {
                key.append((char)(accessTokenBytes[i] | thumbBytes[i % thumbLength]));
            }
//            if (!isAsyncCall) {
//                final Map<String, Object> authHeader = getAuthorizationHeader(dataMap);
//                authHeader.put("gatewayReference", dataMap.get("gatewayReference"));
//                authHeader.put("merchantID", dataMap.get("apiKey"));
//                if (dataMap.containsKey("async")) {
//                    authHeader.put("async", false);
//                }
//                encryptedText = encrypt.encryptText(RequestUtils.gson.toJson(authHeader), key.toString());
//            }
//            else {
//                encryptedText = encrypt.encryptText(dataMap.get("payLoad").toString(), key.toString());
//            }
            encryptedText = encrypt.encryptText(dataMap.get("payLoad").toString(), key.toString());
        }
        catch (Exception ex) {
            final Throwable t;
            EncryptionUtils.log.severe(ex.getMessage());
        }
        payLoadData.put("payLoad", encryptedText.toLowerCase());
        payLoadData.put("apiKey", dataMap.get("apiKey"));
        payLoadData.put("lang", lang);
        return gson.toJson(payLoadData);
    }
    
    static {
        EncryptionUtils.log = Logger.getLogger(EncryptionUtils.class.getName());
    }
}
