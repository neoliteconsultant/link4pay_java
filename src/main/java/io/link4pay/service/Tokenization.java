package io.link4pay.service;

public interface Tokenization {

    /**
     * Save a card.
     */
    void saveCard();


    /**
     * Verify card.
     */
    void verifyCard();

    /**
     * Verify Token.
     */
    void verifyToken();


    /**
     * Delete Token.
     */
    void deleteToken();


    /**
     * Get a customer token.
     */
    void getCustomerToken();

}
