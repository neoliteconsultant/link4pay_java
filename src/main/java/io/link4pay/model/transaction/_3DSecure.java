package io.link4pay.model.transaction;

public class _3DSecure {
    public DeviceFingerprint deviceFingerprint;
    public Exemptions exemptions;
    public ExternalThreeds externalThreeds;
    public String challengeIndicator;
    public String challengeWindowSize;


    public static class DeviceFingerprint{
        public String timezone;
        public String browserColorDepth;
        public String browserLanguage;
        public String browserScreenHeight;
        public String browserScreenWidth;
        public String os;
        public String browserAcceptHeader;
        public String userAgent;
        public String browserJavascriptEnabled;
        public String browserJavaEnabled;
        public String acceptContent;
        public String browserIP;

        public DeviceFingerprint(String timezone, String browserColorDepth, String browserLanguage,
                                 String browserScreenHeight, String browserScreenWidth, String os,
                                 String browserAcceptHeader, String userAgent, String browserJavascriptEnabled,
                                 String browserJavaEnabled, String acceptContent, String browserIP) {
            this.timezone = timezone;
            this.browserColorDepth = browserColorDepth;
            this.browserLanguage = browserLanguage;
            this.browserScreenHeight = browserScreenHeight;
            this.browserScreenWidth = browserScreenWidth;
            this.os = os;
            this.browserAcceptHeader = browserAcceptHeader;
            this.userAgent = userAgent;
            this.browserJavascriptEnabled = browserJavascriptEnabled;
            this.browserJavaEnabled = browserJavaEnabled;
            this.acceptContent = acceptContent;
            this.browserIP = browserIP;
        }
    }
    public static class Exemptions{
        public boolean lowValue;
        public boolean tra;
        public boolean trustedBeneficiary;
        public boolean secureCorporatePayment;
        public boolean delegatedAuthentication;
        public boolean recurringMITExemptionSameAmount;
        public boolean recurringMITExemptionOther;
        public String vmid;

        public Exemptions(boolean lowValue, boolean tra, boolean trustedBeneficiary,
                          boolean secureCorporatePayment, boolean delegatedAuthentication,
                          boolean recurringMITExemptionSameAmount, boolean recurringMITExemptionOther, String vmid) {
            this.lowValue = lowValue;
            this.tra = tra;
            this.trustedBeneficiary = trustedBeneficiary;
            this.secureCorporatePayment = secureCorporatePayment;
            this.delegatedAuthentication = delegatedAuthentication;
            this.recurringMITExemptionSameAmount = recurringMITExemptionSameAmount;
            this.recurringMITExemptionOther = recurringMITExemptionOther;
            this.vmid = vmid;
        }
    }

    public static class ExternalThreeds{
        public String eciCode;
        public String threedsStatus;
        public String acsTransactionId;
        public String dsTransactionId;
        public String threedsServerTransactionId;
        public String threedsVersion;
        public String authenticationValue;
        public String xid;

        public ExternalThreeds(String eciCode, String threedsStatus, String acsTransactionId,
                               String dsTransactionId, String threedsServerTransactionId,
                               String threedsVersion, String authenticationValue, String xid) {
            this.eciCode = eciCode;
            this.threedsStatus = threedsStatus;
            this.acsTransactionId = acsTransactionId;
            this.dsTransactionId = dsTransactionId;
            this.threedsServerTransactionId = threedsServerTransactionId;
            this.threedsVersion = threedsVersion;
            this.authenticationValue = authenticationValue;
            this.xid = xid;
        }
    }
}
