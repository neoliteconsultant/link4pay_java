package io.link4pay.security;

import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.io.IOException;

import javax.crypto.spec.SecretKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.util.Random;
import javax.crypto.Cipher;
import java.util.logging.Logger;

public class AESEncryption {
    private Logger log;
    private Cipher cipher;
    private final Random random = new Random();


    public AESEncryption() {
        try {
            log = Logger.getLogger(AESEncryption.class.getName());
            cipher = Cipher.getInstance("AES/CBC/NoPadding");
            final Field field = Class.forName("javax.crypto.JceSecurity").getDeclaredField("isRestricted");
            field.setAccessible(true);
            final Field modifiersField = Field.class.getDeclaredField("modifiers");
            modifiersField.setAccessible(true);
            modifiersField.setInt(field, field.getModifiers() & 0xFFFFFFEF);
            field.set(null, false);
        } catch (Exception e) {
            log.severe(e.getMessage());
        }
    }

    public String encrypt(final String plainText, final byte[] key, final byte[] iv) {
        try {
            final byte[] clean = padString(plainText).getBytes();
            final int ivSize = iv.length;
            final IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
            final SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
            cipher.init(1, secretKeySpec, ivParameterSpec);
            final byte[] encrypted = cipher.doFinal(clean);
            final byte[] encryptedIVAndText = new byte[ivSize + encrypted.length];
            System.arraycopy(iv, 0, encryptedIVAndText, 0, ivSize);
            System.arraycopy(encrypted, 0, encryptedIVAndText, ivSize, encrypted.length);
            return bytesToHex(encryptedIVAndText);
        } catch (Exception e) {
            log.severe(e.getMessage());

        }

        return null;
    }

    public byte[] decrypt(final String code, final String keyData) {
        byte[] decrypted = null;
        try {
            final byte[] encryptedText = hexToBytes(code);
            if (encryptedText.length == 0) {
                throw new IOException("[decrypt] Incorrect Values");
            }
            final byte[] iv = new byte[cipher.getBlockSize()];
            System.arraycopy(encryptedText, 0, iv, 0, iv.length);
            final IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
            if (code != null && code.isEmpty()) {
                throw new IOException("Empty string");
            }
            final int encryptedSize = encryptedText.length - cipher.getBlockSize();
            final byte[] encryptedBytes = new byte[encryptedSize];
            System.arraycopy(encryptedText, cipher.getBlockSize(), encryptedBytes, 0, encryptedSize);
            final SecretKeySpec key = new SecretKeySpec(keyData.getBytes(), "AES");
            cipher.init(2, key, ivParameterSpec);
            decrypted = cipher.doFinal(encryptedBytes);
        } catch (Exception e) {
            log.severe(e.getMessage());

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
            } else {
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
            buffer[i] = (byte) Integer.parseInt(data.substring(i * 2, i * 2 + 2), 16);
        }
        return buffer;
    }

    public String encryptText(final String input, final String password) {
        final byte[] passwordBytes = password.getBytes(StandardCharsets.UTF_8);
        return this.encryptBytes(input, passwordBytes);
    }

    public String encryptBytes(final String input, final byte[] passwordBytes) {
        final byte[] buffer = new byte[16];
        random.nextBytes(buffer);
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
                throw new IllegalArgumentException("The Key must be exactly 256 or 128 bits long!");
            }
        }
        return encrypt(input, passwordBytes, buffer);
    }

    public String decryptPayload(final String authorizedPayload, final String thumbprint) throws IOException {
        return new String(decrypt(authorizedPayload, thumbprint.substring(0, 32)));
    }


}
