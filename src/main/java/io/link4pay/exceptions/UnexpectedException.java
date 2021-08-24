package io.link4pay.exceptions;

public class UnexpectedException extends Link4PayException {

    private static final long serialVersionUID = 1L;

    public UnexpectedException(String message) {
        super(message);
    }

    public UnexpectedException(String message, Throwable cause) {
        super(message, cause);
    }
}
