package io.link4pay.model.tokenization;

public class TokenizationHttpRequest {
    public String customerID;
    public String merchantID;
    public String tokenID;
    public Customer customer;
    public Card card;
    public String showAllCards;

    public static  class BillingAddress{
        public String firstName;
        public String lastName;
        public String mobileNo;
        public String emailId;
    }

    public static  class Customer{
        public BillingAddress billingAddress;
    }

    public static class Card{
        public String cardNumber;
        public String expMonth;
        public String expYear;
        public String nameOnCard;
        public String encCardNumber;
        public String encAlgorithm;
        public String keySequenceNumber;
        public String acquirer;
    }
}
