package io.link4pay.model;

import com.google.gson.Gson;

import static io.link4pay.model.TransactionHttpRequest.*;

public class TransactionRequest extends Request {
    private Gson gson = new Gson();
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
    private boolean hostedPage;
    private boolean async;
    private boolean payout;
    private String tokenId;
    private String paymentType;
    private String paymentMode;
    private String bankCode;
    private String holder;
    private String bic;
    private String iban;
    private String successURL;
    private String failURL;
    private String cancelURL;
    private String showConfirmationPage;
    private String cartURL;
    private String productURL;

    public TransactionRequest merchantID(String merchantID) {
        this.merchantID = merchantID;
        return this;
    }

    public TransactionRequest customerID(String customerID) {
        this.customerID = customerID;
        return this;
    }


    public TransactionRequest firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public TransactionRequest lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public TransactionRequest mobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
        return this;
    }

    public TransactionRequest emailId(String emailId) {
        this.emailId = emailId;
        return this;
    }

    public TransactionRequest addressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
        return this;
    }

    public TransactionRequest addressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
        return this;
    }

    public TransactionRequest city(String city) {
        this.city = city;
        return this;
    }

    public TransactionRequest state(String state) {
        this.state = state;
        return this;
    }

    public TransactionRequest zip(String zip) {
        this.zip = zip;
        return this;
    }

    public TransactionRequest country(String country) {
        this.country = country;
        return this;
    }

    public TransactionRequest txnAmount(double txnAmount) {
        this.txnAmount = txnAmount;
        return this;
    }

    public TransactionRequest currencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
        return this;
    }

    public TransactionRequest txnReference(String txnReference) {
        this.txnReference = txnReference;
        return this;
    }

    public TransactionRequest hostedPage(boolean hostedPage) {
        this.hostedPage = hostedPage;
        return this;
    }

    public TransactionRequest tokenId(String tokenId){
        this.tokenId = tokenId;
        return this;
    }

    public TransactionRequest async(boolean async) {
        this.async= async;
        return this;
    }

    public TransactionRequest payout(boolean payout) {
        this.payout= payout;
        return this;
    }

    public TransactionRequest paymentType(String paymentType) {
        this.paymentType = paymentType;
        return this;

    }

    public TransactionRequest paymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
        return this;

    }

    public TransactionRequest bankCode(String bankCode) {
        this.bankCode = bankCode;
        return this;

    }

    public TransactionRequest holder(String holder) {
        this.holder = holder;
        return this;

    }

    public TransactionRequest bic(String bic) {
        this.bic = bic;
        return this;
    }

    public TransactionRequest iban(String iban) {
        this.iban = iban;
        return this;
    }

    public TransactionRequest successURL(String successURL) {
        this.successURL = successURL;
        return this;
    }

    public TransactionRequest failURL(String failURL) {
        this.failURL = failURL;
        return this;
    }

    public TransactionRequest cancelURL(String cancelURL) {
        this.cancelURL = cancelURL;
        return this;
    }

    public TransactionRequest showConfirmationPage(String showConfirmationPage) {
        this.showConfirmationPage = showConfirmationPage;
        return this;
    }

    public TransactionRequest cartURL(String cartURL) {
        this.cartURL = cartURL;
        return this;
    }

    public TransactionRequest productURL(String productURL) {
        this.productURL = productURL;
        return this;
    }

    @Override
    public String toJSON() {

        TransactionHttpRequest transactionHttpRequest = new TransactionHttpRequest();
        transactionHttpRequest.lang = "en";

        Merchant merchant = new Merchant();
        merchant.merchantID = merchantID;
        merchant.customerID = customerID;
        transactionHttpRequest.merchant = merchant;

        BillingAddress billingAddress = new BillingAddress();
        billingAddress.firstName = firstName;
        billingAddress.lastName = lastName;
        billingAddress.mobileNo = mobileNo;
        billingAddress.emailId = emailId;
        billingAddress.addressLine1 = addressLine1;
        billingAddress.addressLine2 = addressLine2;
        billingAddress.city = city;
        billingAddress.state = state;
        billingAddress.zip = zip;
        billingAddress.country = country;

        Customer customer = new Customer();
        customer.billingAddress = billingAddress;
        transactionHttpRequest.customer = customer;

        PaymentDetail paymentDetail = new PaymentDetail();
        paymentDetail.tokenid = tokenId;

        Transaction transaction = new Transaction();
        transaction.txnAmount = String.valueOf(txnAmount);
        transaction.currencyCode = currencyCode;
        transaction.txnReference = txnReference;
        transaction.hostedPage = hostedPage;
        transaction.payout = String.valueOf(payout);
        transaction.paymentDetail =paymentDetail;
        transaction.async = async;
        transactionHttpRequest.transaction = transaction;

        Url url = new Url();
        url.successURL = successURL;
        url.failURL = failURL;
        url.cancelURL = cancelURL;
        url.showConfirmationPage = showConfirmationPage;
        url.cartURL = cartURL;
        url.productURL = productURL;
        transactionHttpRequest.url = url;

        return gson.toJson(transactionHttpRequest);

    }
}
