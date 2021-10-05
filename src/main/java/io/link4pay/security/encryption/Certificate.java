

package io.link4pay.security.encryption;

import java.security.Provider;
import java.security.Security;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import java.io.IOException;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.security.cert.CertificateEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.MessageDigest;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.io.InputStream;
import java.security.cert.X509Certificate;
import java.util.logging.Logger;

public class Certificate
{
    static Logger log;
    private X509Certificate x509Certificate;
    private static final String BC_PROVIDER = "BC";
    private String certPath;
    
    public Certificate(final String certPath, final String keyPath) {
        this.certPath = "";
        this.certPath = certPath;
    }
    
    public Certificate(final InputStream certStream, final InputStream keyStream) {
        this.certPath = "";
        try {
            final CertificateFactory fact = CertificateFactory.getInstance("X.509");
            this.x509Certificate = (X509Certificate)fact.generateCertificate(certStream);
        }
        catch (CertificateException e) {
            Certificate.log.severe(e.getMessage());
        }
    }
    
    public String getThumbPrint() throws NoSuchAlgorithmException, CertificateEncodingException {
        final MessageDigest md = MessageDigest.getInstance("SHA-1");
        final byte[] der = this.x509Certificate.getEncoded();
        md.update(der);
        final byte[] digest = md.digest();
        final StringBuilder sb = new StringBuilder();
        for (final byte element : digest) {
            sb.append(":").append(String.format("%02X", element));
        }
        return sb.substring(1).toUpperCase();
    }
    
    public void getCertificateDetails() {
        if (this.x509Certificate != null) {
            return;
        }
        try {
            final CertificateFactory fact = CertificateFactory.getInstance("X.509");
            this.x509Certificate = (X509Certificate)fact.generateCertificate(new BufferedInputStream(new FileInputStream(this.certPath)));
        }
        catch (CertificateException | IOException ex2) {
            final Exception ex;
            Certificate.log.severe(ex2.getMessage());
        }
    }
    
    static {
        Certificate.log = Logger.getLogger(Certificate.class.getName());
        Security.addProvider(new BouncyCastleProvider());
        if (Security.getProvider("BC") == null) {
            Certificate.log.severe("Bouncy Castle provider is NOT available");
        }
    }
}
