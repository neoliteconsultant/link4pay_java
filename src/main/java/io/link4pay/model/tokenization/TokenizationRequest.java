package io.link4pay.model.tokenization;

import com.google.gson.Gson;
import io.link4pay.model.Request;
import io.link4pay.model.tokenization.TokenizationHttpRequest;

import static io.link4pay.model.tokenization.TokenizationHttpRequest.*;

public class TokenizationRequest extends Request {
    private Gson gson = new Gson();
    private String merchantID;
    private String customerID;
    private String firstName;
    private String lastName;
    private String mobileNo;
    private String emailId;
    private String cardNumber;
    private String expMonth;
    private String expYear;
    private String nameOnCard;
    private String encCardNumber;
    private String encAlgorithm;
    private String keySequenceNumber;
    private String acquirer;
    private String showAllCards;
    private String tokenID;


    public TokenizationRequest merchantID(String merchantID) {
        this.merchantID = merchantID;
        return this;
    }

    public TokenizationRequest customerID(String customerID) {
        this.customerID = customerID;
        return this;
    }


    public TokenizationRequest firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public TokenizationRequest lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public TokenizationRequest mobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
        return this;
    }

    public TokenizationRequest emailId(String emailId) {
        this.emailId = emailId;
        return this;
    }

    public TokenizationRequest cardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
        return this;
    }

    public TokenizationRequest expMonth(String expMonth) {
        this.expMonth = expMonth;
        return this;
    }

    public TokenizationRequest expYear(String expYear) {
        this.expYear = expYear;
        return this;
    }

    public TokenizationRequest nameOnCard(String nameOnCard) {
        this.nameOnCard = nameOnCard;
        return this;
    }

    public TokenizationRequest encCardNumber(String encCardNumber) {
        this.encCardNumber = encCardNumber;
        return this;
    }

    public TokenizationRequest encAlgorithm(String encAlgorithm) {
        this.encAlgorithm = encAlgorithm;
        return this;
    }

    public TokenizationRequest keySequenceNumber(String keySequenceNumber) {
        this.keySequenceNumber = keySequenceNumber;
        return this;
    }

    public TokenizationRequest acquirer(String acquirer) {
        this.acquirer = acquirer;
        return this;
    }

    public TokenizationRequest showAllCards(String showAllCards) {
        this.showAllCards = showAllCards;
        return this;
    }



    public TokenizationRequest tokenID(String tokenID){
        this.tokenID = tokenID;
        return this;
    }






    @Override
    public String toJSON() {

        TokenizationHttpRequest tokenizationHttpRequest = new TokenizationHttpRequest();
        tokenizationHttpRequest.customerID = customerID;
        tokenizationHttpRequest.merchantID = merchantID;
        tokenizationHttpRequest.tokenID = tokenID;

        if(firstName!=null||lastName!=null||mobileNo!=null||emailId!=null) {
            BillingAddress billingAddress = new BillingAddress();
            billingAddress.firstName = firstName;
            billingAddress.lastName = lastName;
            billingAddress.mobileNo = mobileNo;
            billingAddress.emailId = emailId;

            Customer customer = new Customer();
            customer.billingAddress = billingAddress;
            tokenizationHttpRequest.customer = customer;
        }

        Card card = new Card();
        if(cardNumber!=null|| expMonth!=null||expYear!=null) {
            card.cardNumber = cardNumber;
            card.expMonth = expMonth;
            card.expYear = expYear;
            card.nameOnCard = nameOnCard;
            card.encCardNumber = encCardNumber;
            card.encAlgorithm = encAlgorithm;
            card.keySequenceNumber = String.valueOf(keySequenceNumber);
            card.acquirer = acquirer;
            tokenizationHttpRequest.card = card;
        }


        if(showAllCards!=null) {
            tokenizationHttpRequest.showAllCards = showAllCards;
        }

        return gson.toJson(tokenizationHttpRequest);

    }
}
