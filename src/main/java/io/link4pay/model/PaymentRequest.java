package io.link4pay.model;

import com.google.gson.Gson;
import io.link4pay.model.requests.PaymentWithHpp;
import static io.link4pay.model.requests.PaymentWithHpp.*;

public class PaymentRequest extends Request{
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

        PaymentWithHpp paymentWithHPP = new PaymentWithHpp();
        paymentWithHPP.lang="en";

        Merchant merchant = new Merchant();
        merchant.merchantID = merchantID;
        merchant.customerID = customerID;
        paymentWithHPP.merchant = merchant;

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
        paymentWithHPP.customer = customer;

        Transaction transaction = new Transaction();
        transaction.txnAmount = String.valueOf(txnAmount);
        transaction.currencyCode = currencyCode;
        transaction.txnReference = txnReference;
        transaction.payout = "true/false";
        paymentWithHPP.transaction = transaction;

        Url url = new Url();
        url.successURL = successURL;
        url.failURL = failURL;
        url.cancelURL = cancelURL;
        url.showConfirmationPage = showConfirmationPage;
        url.cartURL = cartURL;
        url.productURL = productURL;
        paymentWithHPP.url = url;

        return gson.toJson(paymentWithHPP);

    }
}
