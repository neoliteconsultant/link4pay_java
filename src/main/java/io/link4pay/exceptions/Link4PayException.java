package io.link4pay.exceptions;

public class Link4PayException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public Link4PayException(String message, Throwable cause) {
        super(message, cause);
    }

    public Link4PayException(String message) {
        super(message);
    }

    public Link4PayException() {
        super();
    }
}
