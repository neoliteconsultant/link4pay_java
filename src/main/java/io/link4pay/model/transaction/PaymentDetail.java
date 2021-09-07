package io.link4pay.model.transaction;

public class PaymentDetail {
    public String tokenid;
    public String cardNumber;
    public String cardType;
    public String expYear;
    public String expMonth;
    public String nameOnCard;
    public String saveDetails;
    public String cvv;

    public PaymentDetail(String tokenid) {
        this.tokenid = tokenid;
    }

    public PaymentDetail(String cardNumber, String cardType, String expYear, String expMonth, String nameOnCard, String saveDetails, String cvv) {
        this.cardNumber = cardNumber;
        this.cardType = cardType;
        this.expYear = expYear;
        this.expMonth = expMonth;
        this.nameOnCard = nameOnCard;
        this.saveDetails = saveDetails;
        this.cvv = cvv;
    }
}
