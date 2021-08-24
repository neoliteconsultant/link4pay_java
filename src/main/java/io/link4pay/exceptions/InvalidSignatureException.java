package io.link4pay.exceptions;

public class InvalidSignatureException extends Link4PayException {
    private static final long serialVersionUID = 1L;

    public InvalidSignatureException(String message) {
        super(message);
    }

    public InvalidSignatureException() {
        super();
    }
}
