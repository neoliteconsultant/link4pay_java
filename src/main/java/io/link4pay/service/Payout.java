package io.link4pay.service;

public interface Payout {

    /**
     * Tokenize payout.
     */
    void tokenizePayout();


    /**
     * Make card payout.
     */
    void makeCardPayout();

}
