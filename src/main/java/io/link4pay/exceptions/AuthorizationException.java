package io.link4pay.exceptions;

public class AuthorizationException extends Link4PayException {
    private static final long serialVersionUID = 1L;
    
    public AuthorizationException(String message) {
        super(message);
    }
}
