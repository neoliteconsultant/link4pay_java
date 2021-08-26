package io.link4pay.model.requests;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PaymentWithHpp {
    public String lang;
    public Merchant merchant;
    public Customer customer;
    public Transaction transaction;
    public DynamicDescriptor dynamicDescriptor;
    public Url url;
    public Summary summary;
    public List<Item> items;
    public CustomData customData;

    public static class Merchant{
        public String merchantID;
        public String customerID;
    }

    public static class BillingAddress{
        public String firstName;
        public String lastName;
        public String mobileNo;
        public String emailId;
        public String addressLine1;
        public String addressLine2;
        public String city;
        public String state;
        public String zip;
        public String country;
    }

    public class ShippingAddress{
        public String sFirstName;
        public String sLastName;
        public String sMobileNo;
        public String sEmailId;
        public String sAddressLine1;
        public String sAddressLine2;
        public String sCity;
        public String sState;
        public String sZip;
        public String sCountry;
    }

    public static class Customer{
        public BillingAddress billingAddress;
        public ShippingAddress shippingAddress;
    }

    public class Exemptions{
        public boolean lowValue;
        public boolean tra;
        public boolean trustedBeneficiary;
        public boolean secureCorporatePayment;
        public boolean delegatedAuthentication;
        public boolean recurringMITExemptionSameAmount;
        public boolean recurringMITExemptionOther;
        public String vmid;
    }

    public class _3DSecure{
        public Exemptions exemptions;
        public String challengeIndicator;
        public String challengeWindowSize;
    }

    public static class Transaction{
        public String txnAmount;
        public String currencyCode;
        public String txnReference;
        public String payout;
        public boolean isApp;
        @SerializedName("3DSecure")
        public _3DSecure _3DSecure;
    }

    public class DynamicDescriptor{
        public String name;
        public String email;
        public String mobile;
    }

    public static class Url{
        public String successURL;
        public String failURL;
        public String cancelURL;
        public String showConfirmationPage;
        public String cartURL;
        public String productURL;
        public String iFrame;
    }

    public class Details{
        public String subtotal;
        public String tax;
        public String shippingCharges;
    }

    public class Discount{
        public String discountValue;
        public String couponCode;
        public String couponCodeDetails;
    }

    public class Summary{
        public String totalValue;
        public Details details;
        public Discount discount;
    }

    public class Item{
        public String itemName;
        public String itemId;
        public String itemPricePerUnit;
        public String itemQuantity;
    }

    public class CustomData{
        public String customData1;
        public String customData2;
        public String customData3;
        public String customData4;
        public String customData5;
        public String site;
    }

}
