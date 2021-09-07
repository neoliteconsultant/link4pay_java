package io.link4pay.model.transaction;

import com.google.gson.Gson;
import io.link4pay.model.Request;

import java.util.List;

import static io.link4pay.model.transaction.TransactionHttpRequest.*;

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
    private String shippingFirstName;
    private String shippingLastName;
    private String shippingMobileNo;
    private String shippingEmailId;
    private String shippingAddressLine1;
    private String shippingAddressLine2;
    private String shippingCity;
    private String shippingState;
    private String shippingZip;
    private String shippingCountry;

    private double txnAmount;
    private String currencyCode;
    private String txnReference;
    public boolean isApp;
    private boolean hostedPage;
    private boolean async;
    private String payout;
    private PaymentDetail paymentDetail;
//    private String tokenId;
    private DynamicDescriptor dynamicDescriptor;
    private _3DSecure _3dSecure;
    private String paymentType;
    private String paymentMode;
    private String bankCode;
    private String holder;
    private String bic;
    private String iban;
    private String totalValue;
    private String subtotal;
    private String tax;
    private String shippingCharges;
    private String discountValue;
    private String couponCode;
    private String couponCodeDetails;
    private String customData1;
    private String customData2;
    private String customData3;
    private String customData4;
    private String customData5;
    private String site;
    public  String showAllCards;
    public List<Item> items;
    private String successURL;
    private String failURL;
    private String cancelURL;
    private String showConfirmationPage;
    private String cartURL;
    private String productURL;
    private String iFrame;

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

    public TransactionRequest shippingFirstName(String shippingFirstName) {
        this.shippingFirstName = shippingFirstName;
        return this;
    }

    public TransactionRequest shippingLastName(String shippingLastName) {
        this.shippingLastName = shippingLastName;
        return this;
    }

    public TransactionRequest shippingMobileNo(String shippingMobileNo) {
        this.shippingMobileNo = shippingMobileNo;
        return this;
    }

    public TransactionRequest shippingEmailId(String shippingEmailId) {
        this.shippingEmailId = shippingEmailId;
        return this;
    }

    public TransactionRequest shippingAddressLine1(String shippingAddressLine1) {
        this.shippingAddressLine1 = shippingAddressLine1;
        return this;
    }

    public TransactionRequest shippingAddressLine2(String shippingAddressLine2) {
        this.shippingAddressLine2 = shippingAddressLine2;
        return this;
    }

    public TransactionRequest shippingCity(String shippingCity) {
        this.shippingCity = shippingCity;
        return this;
    }

    public TransactionRequest shippingState(String shippingState) {
        this.shippingState = shippingState;
        return this;
    }

    public TransactionRequest shippingZip(String shippingZip) {
        this.shippingZip = shippingZip;
        return this;
    }

    public TransactionRequest shippingCountry(String shippingCountry) {
        this.shippingCountry = shippingCountry;
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

    public TransactionRequest dynamicDescriptor(DynamicDescriptor dynamicDescriptor) {
        this.dynamicDescriptor = dynamicDescriptor;
        return this;
    }

    public TransactionRequest paymentDetail(PaymentDetail paymentDetail){
        this.paymentDetail =paymentDetail;
        return this;

    }
    public TransactionRequest _3DSecure(_3DSecure _3dSecure){
         this._3dSecure = _3dSecure;
        return this;
    }

    public TransactionRequest hostedPage(boolean hostedPage) {
        this.hostedPage = hostedPage;
        return this;
    }

    public TransactionRequest showAllCards(String showAllCards) {
        this.showAllCards = showAllCards;
        return this;
    }

    public TransactionRequest isApp(boolean isApp) {
        this.isApp = isApp;
        return this;
    }

    /*
    public TransactionRequest tokenId(String tokenId) {
        this.tokenId = tokenId;
        return this;
    }*/

    public TransactionRequest async(boolean async) {
        this.async = async;
        return this;
    }

    public TransactionRequest payout(String payout) {
        this.payout = payout;
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

    public TransactionRequest totalValue(String totalValue) {
        this.totalValue = totalValue;
        return this;
    }

    public TransactionRequest subtotal(String subtotal) {
        this.subtotal = subtotal;
        return this;
    }

    public TransactionRequest tax(String tax) {
        this.tax = tax;
        return this;
    }

    public TransactionRequest shippingCharges(String shippingCharges) {
        this.shippingCharges = shippingCharges;
        return this;
    }

    public TransactionRequest discountValue(String discountValue) {
        this.discountValue = discountValue;
        return this;
    }

    public TransactionRequest couponCode(String couponCode) {
        this.couponCode = couponCode;
        return this;
    }

    public TransactionRequest couponCodeDetails(String couponCodeDetails) {
        this.couponCodeDetails = couponCodeDetails;
        return this;
    }

    public TransactionRequest customData1(String customData1) {
        this.customData1 = customData1;
        return this;
    }

    public TransactionRequest customData2(String customData2) {
        this.customData2 = customData2;
        return this;
    }

    public TransactionRequest customData3(String customData3) {
        this.customData3 = customData3;
        return this;
    }

    public TransactionRequest customData4(String customData4) {
        this.customData4 = customData4;
        return this;
    }

    public TransactionRequest customData5(String customData5) {
        this.customData5 = customData5;
        return this;
    }

    public TransactionRequest site(String site) {
        this.site = site;
        return this;
    }


    public TransactionRequest items(List<Item> items) {
        this.items = items;
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

    public TransactionRequest iFrame(String iFrame) {
        this.iFrame = iFrame;
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

        if (shippingAddressLine1 != null ||
                shippingAddressLine2 != null ||
                shippingCity != null) {
            ShippingAddress shippingAddress = new ShippingAddress();
            shippingAddress.sFirstName = shippingFirstName;
            shippingAddress.sLastName = shippingLastName;
            shippingAddress.sMobileNo = shippingMobileNo;
            shippingAddress.sEmailId = shippingEmailId;
            shippingAddress.sAddressLine1 = shippingAddressLine1;
            shippingAddress.sAddressLine2 = shippingAddressLine2;
            shippingAddress.sCity = shippingCity;
            shippingAddress.sState = shippingState;
            shippingAddress.sZip = shippingZip;
            shippingAddress.sCountry = shippingCountry;

            customer.shippingAddress = shippingAddress;
        }


        transactionHttpRequest.customer = customer;


//        PaymentDetail paymentDetail = new PaymentDetail();
//        paymentDetail.tokenid = tokenId;


        Transaction transaction = new Transaction();
        transaction.txnAmount = String.valueOf(txnAmount);
        transaction.currencyCode = currencyCode;
        transaction.txnReference = txnReference;
        transaction.hostedPage = hostedPage;
        transaction.payout = payout;
        if(paymentDetail!=null) {
            transaction.paymentDetail = paymentDetail;
        }
        transaction.async = async;
        transaction.isApp = isApp;
        if(_3dSecure!=null){
            transaction._3DSecure = _3dSecure;
        }
        transactionHttpRequest.transaction = transaction;





        transactionHttpRequest.dynamicDescriptor = dynamicDescriptor;

        Url url = new Url();
        url.successURL = successURL;
        url.failURL = failURL;
        url.cancelURL = cancelURL;
        url.showConfirmationPage = showConfirmationPage;
        url.cartURL = cartURL;
        url.productURL = productURL;
        url.iFrame = iFrame;
        transactionHttpRequest.url = url;


        transactionHttpRequest.items = items;

        if (totalValue != null) {
            Summary summary = new Summary();
            summary.totalValue = totalValue;

            Details details = new Details();
            details.subtotal = subtotal;
            details.tax = tax;
            details.shippingCharges = shippingCharges;
            summary.details = details;

            Discount discount = new Discount();
            discount.discountValue = discountValue;
            discount.couponCode = couponCode;
            discount.couponCodeDetails = couponCodeDetails;
            summary.discount = discount;

            transactionHttpRequest.summary = summary;
        }

        CustomData customData = new CustomData();
        if (customData1 != null ||
                customData2 != null ||
                customData3 != null ||
                customData4 != null ||
                customData5 != null || site != null) {
            customData.customData1 = customData1;
            customData.customData2 = customData2;
            customData.customData3 = customData3;
            customData.customData4 = customData4;
            customData.customData5 = customData5;
            customData.site = site;

            transactionHttpRequest.customData = customData;
        }

        if(showAllCards!=null) {
            transactionHttpRequest.showAllCards = showAllCards;
        }


        return gson.toJson(transactionHttpRequest);

    }
}
