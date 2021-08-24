package io.link4pay.model;

public class PaymentRequest extends Request{
    private String merchantID;
    private String customerID;
    private String firstName;
    private String lastName;
    private String mobileNo;
    private String emailId;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String zip;
    private String country;
    private double txnAmount;
    private String currencyCode;
    private String txnReference;
    private String successURL;
    private String failURL;
    private String cancelURL;
    private String showConfirmationPage;
    private String cartURL;
    private String productURL;

    public PaymentRequest merchantID(String merchantID) {
        this.merchantID = merchantID;
        return this;
    }

    public PaymentRequest customerID(String customerID) {
        this.customerID = customerID;
        return this;
    }


    public PaymentRequest firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public PaymentRequest lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public PaymentRequest mobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
        return this;
    }

    public PaymentRequest emailId(String emailId) {
        this.emailId = emailId;
        return this;
    }

    public PaymentRequest addressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
        return this;
    }

    public PaymentRequest addressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
        return this;
    }

    public PaymentRequest city(String city) {
        this.city = city;
        return this;
    }

    public PaymentRequest state(String state) {
        this.state = state;
        return this;
    }

    public PaymentRequest zip(String zip) {
        this.zip = zip;
        return this;
    }

    public PaymentRequest country(String country) {
        this.country = country;
        return this;
    }

    public PaymentRequest txnAmount(double txnAmount) {
        this.txnAmount = txnAmount;
        return this;
    }

    public PaymentRequest currencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
        return this;
    }

    public PaymentRequest txnReference(String txnReference) {
        this.txnReference = txnReference;
        return this;
    }

    public PaymentRequest successURL(String successURL) {
        this.successURL = successURL;
        return this;
    }
    public PaymentRequest failURL(String failURL) {
        this.failURL = failURL;
        return this;
    }
    public PaymentRequest cancelURL(String cancelURL) {
        this.cancelURL = cancelURL;
        return this;
    }
    public PaymentRequest showConfirmationPage(String showConfirmationPage) {
        this.showConfirmationPage = showConfirmationPage;
        return this;
    }
    public PaymentRequest cartURL(String cartURL) {
        this.cartURL= cartURL;
        return this;
    }
    public PaymentRequest productURL(String productURL) {
        this.productURL= productURL;
        return this;
    }

    @Override
    public String toJSON() {

        String request ="{\n" +
                "  \"lang\": \"en\",\n" +
                "  \"merchant\":\n" +
                "  {\n" +
                "    \"merchantID\": \"MER09821725\",\n" +
                "    \"customerID\": \"CUS77743201\"\n" +
                "  },\n" +
                "  \"customer\":\n" +
                "  {\n" +
                "    \"billingAddress\":\n" +
                "    {\n" +
                "      \"firstName\": \"Joe\",\n" +
                "      \"lastName\": \"Test\",\n" +
                "      \"mobileNo\": \"3123456789\",\n" +
                "      \"emailId\": \"test@test.test\",\n" +
                "      \"addressLine1\": \"abc\",\n" +
                "      \"addressLine2\": \"abc\",\n" +
                "      \"city\": \"abc\",\n" +
                "      \"state\": \"abc\",\n" +
                "      \"zip\": \"AR12345\",\n" +
                "      \"country\":\"CY\"\n" +
                "    },\n" +
                "    \"shippingAddress\":\n" +
                "    {\n" +
                "      \"sFirstName\": \"Joe\",\n" +
                "      \"sLastName\": \"Test\",\n" +
                "      \"sMobileNo\": \"3123456789\",\n" +
                "      \"sEmailId\": \"test@test.test\",\n" +
                "      \"sAddressLine1\": \"abc\",\n" +
                "      \"sAddressLine2\": \"abc\",\n" +
                "      \"sCity\": \"abc\",\n" +
                "      \"sState\": \"abc\",\n" +
                "      \"sZip\": \"AR12345\",\n" +
                "      \"sCountry\":\"CY\"\n" +
                "    }\n" +
                "  },\n" +
                "  \"transaction\":\n" +
                "  {\n" +
                "    \"txnAmount\": \"19.99\",\n" +
                "    \"currencyCode\": \"EUR\",\n" +
                "    \"txnReference\": \"REF0013tt22112\",\n" +
                "    \"payout\": \"true/false\",\n" +
                "    \"isApp\": true,\n" +
                "    \"3DSecure\":{\n" +
                "\n" +
                "     \"exemptions\":{\n" +
                "        \"lowValue\":true,\n" +
                "        \"tra\": true,\n" +
                "        \"trustedBeneficiary\":true,\n" +
                "        \"secureCorporatePayment\":true,\n" +
                "        \"delegatedAuthentication\":true,\n" +
                "        \"recurringMITExemptionSameAmount\" : true,\n" +
                "        \"recurringMITExemptionOther\" : true,\n" +
                "        \"vmid\":\"12345\"\n" +
                "    },\n" +
                "       \"challengeIndicator\":\"01\",\n" +
                "       \"challengeWindowSize\":\"05\"\n" +
                "  }\n" +
                "  },\n" +
                "  \"dynamicDescriptor\":\n" +
                "  {\n" +
                "    \"name\":\"COMPANY NAME LTD\",\n" +
                "    \"email\":\"joedoe@example.com\",\n" +
                "    \"mobile\":\"123456798\"\n" +
                "  },\n" +
                "  \"url\":\n" +
                "  {\n" +
                "    \"successURL\": \"http://www.domain.com/SuccessResponse.html\",\n" +
                "    \"failURL\": \"http://www.domain.com/FailResponse.html\",\n" +
                "    \"cancelURL\": \"http://www.domain.com/CancelResponse.html\",\n" +
                "    \"showConfirmationPage\": \"true\",\n" +
                "    \"cartURL\": \"http://www.domain.com/Cart.html\",\n" +
                "    \"productURL\": \"http://www.domain.com/Product.html\",\n" +
                "    \"iFrame\": \"true/false\"\n" +
                " },\n" +
                "  \"summary\":\n" +
                "  {\n" +
                "    \"totalValue\": \"19.07\",\n" +
                "    \"details\":\n" +
                "    {\n" +
                "      \"subtotal\": \"19.41\",\n" +
                "      \"tax\": \"0.03\",\n" +
                "      \"shippingCharges\": \"0.55\"\n" +
                "    },\n" +
                "    \"discount\":\n" +
                "    {\n" +
                "      \"discountValue\": \"0.40\",\n" +
                "      \"couponCode\": \"FIRST40\",\n" +
                "      \"couponCodeDetails\": \"Get $0.4 off on every transaction. *T&C apply\"\n" +
                "    }\n" +
                "  },\n" +
                "  \"items\":\n" +
                "  [\n" +
                "    {\n" +
                "      \"itemName\": \"RBK fitness shoes\",\n" +
                "      \"itemId\": \"ITM001\",\n" +
                "      \"itemPricePerUnit\": \"2.49\",\n" +
                "      \"itemQuantity\": \"2\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"itemName\": \"Nike DriFit T-shirt\",\n" +
                "      \"itemId\": \"ITM002\",\n" +
                "      \"itemPricePerUnit\": \"1.99\",\n" +
                "      \"itemQuantity\": \"1\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"customData\":{\n" +
                "  \"customData1\":\"\",\n" +
                "  \"customData2\":\"\",\n" +
                "  \"customData3\":\"\",\n" +
                "  \"customData4\":\"\",\n" +
                "  \"customData5\":\"\",\n" +
                "  \"site\":\"\"\n" +
                "  }\n" +
                " }\n";
        return request;
    }
}
