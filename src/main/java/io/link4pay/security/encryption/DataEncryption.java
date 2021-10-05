

package io.link4pay.security.encryption;

import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.io.IOException;


import java.security.Key;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.util.Random;
import javax.crypto.Cipher;
import java.util.logging.Logger;

public class DataEncryption
{
    static Logger log;
    private static Cipher cipher;
    private static final Random random;
    
    public static String encryptAES(final String plainText, final byte[] key, final byte[] iv) throws Exception {
        try {
            final byte[] clean = padString(plainText).getBytes();
            final int ivSize = iv.length;
            final IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
            final SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
            DataEncryption.cipher.init(1, secretKeySpec, ivParameterSpec);
            final byte[] encrypted = DataEncryption.cipher.doFinal(clean);
            final byte[] encryptedIVAndText = new byte[ivSize + encrypted.length];
            System.arraycopy(iv, 0, encryptedIVAndText, 0, ivSize);
            System.arraycopy(encrypted, 0, encryptedIVAndText, ivSize, encrypted.length);
            return bytesToHex(encryptedIVAndText);
        }
        catch (Exception e) {
            DataEncryption.log.severe(e.getMessage());
            throw new Exception("Cannot encrypt data: " + e.getMessage());
        }
    }
    
    public static byte[] decrypt(final String code, final String keyData) throws IOException {
        byte[] decrypted;
        try {
            final byte[] encryptedText = hexToBytes(code);
            if (encryptedText.length == 0) {
                throw new IOException("[decrypt] Incorrect Values");
            }
            final byte[] iv = new byte[DataEncryption.cipher.getBlockSize()];
            System.arraycopy(encryptedText, 0, iv, 0, iv.length);
            final IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
            if (code != null && code.isEmpty()) {
                throw new IOException("Empty string");
            }
            final int encryptedSize = encryptedText.length - DataEncryption.cipher.getBlockSize();
            final byte[] encryptedBytes = new byte[encryptedSize];
            System.arraycopy(encryptedText, DataEncryption.cipher.getBlockSize(), encryptedBytes, 0, encryptedSize);
            final SecretKeySpec key = new SecretKeySpec(keyData.getBytes(), "AES");
            DataEncryption.cipher.init(2, key, ivParameterSpec);
            decrypted = DataEncryption.cipher.doFinal(encryptedBytes);
        }
        catch (Exception e) {
            DataEncryption.log.severe(e.getMessage());
            throw new IOException("[decrypt] " + e.getMessage());
        }
        return decrypted;
    }
    
    private static String padString(final String source) {
        final StringBuilder data = new StringBuilder(source);
        final char paddingChar = ' ';
        final int size = 16;
        final int x = data.length() % size;
        for (int padLength = size - x, i = 0; i < padLength; ++i) {
            data.append(paddingChar);
        }
        return data.toString();
    }
    
    private static String bytesToHex(final byte[] data) {
        if (data == null) {
            return "";
        }
        final int len = data.length;
        final StringBuilder str = new StringBuilder();
        for (int i = 0; i < len; ++i) {
            if ((data[i] & 0xFF) < 16) {
                str.append("0" + Integer.toHexString(data[i] & 0xFF));
            }
            else {
                str.append(String.format("%02X", data[i] & 0xFF));
            }
        }
        return str.toString();
    }
    
    private static byte[] hexToBytes(final String data) {
        if (data == null || data.length() < 2) {
            return new byte[0];
        }
        final int len = data.length() / 2;
        final byte[] buffer = new byte[len];
        for (int i = 0; i < len; ++i) {
            buffer[i] = (byte)Integer.parseInt(data.substring(i * 2, i * 2 + 2), 16);
        }
        return buffer;
    }
    
    public String encryptText(final String input, final String password) throws Exception{
        final byte[] passwordBytes = password.getBytes(StandardCharsets.UTF_8);
        return this.encryptBytes(input, passwordBytes);
    }
    
    public String encryptBytes(final String input, final byte[] passwordBytes) throws Exception {
        final byte[] buffer = new byte[16];
        DataEncryption.random.nextBytes(buffer);
        final StringBuilder hex2 = new StringBuilder(buffer.length * 2);
        for (final byte b : buffer) {
            hex2.append(String.format("%02X", b));
        }
        switch (passwordBytes.length * 8) {
            case 256: {
                break;
            }
            case 128: {
                break;
            }
            default: {
                throw new Exception("The Key must be exactly 256 or 128 bits long!");
            }
        }
        return encryptAES(input, passwordBytes, buffer);
    }
    
    public static String decryptPayload(final String authorizedPayload, final String thumbprint) throws IOException {
        return new String(decrypt(authorizedPayload, thumbprint.substring(0, 32)));
    }
    
    static {
        DataEncryption.log = Logger.getLogger(DataEncryption.class.getName());
        random = new Random();
        try {
            DataEncryption.cipher = Cipher.getInstance("AES/CBC/NoPadding");
//            final Field field = Class.forName("javax.crypto.JceSecurity").getDeclaredField("isRestricted");
//            field.setAccessible(true);
//            final Field modifiersField = Field.class.getDeclaredField("modifiers");
//            modifiersField.setAccessible(true);
//            modifiersField.setInt(field, field.getModifiers() & 0xFFFFFFEF);
//            field.set(null, false);
        }
        catch (Exception e) {
            DataEncryption.log.severe(e.getMessage());
        }
    }
}
