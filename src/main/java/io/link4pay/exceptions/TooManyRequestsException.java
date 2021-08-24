package io.link4pay.exceptions;

public class TooManyRequestsException extends Link4PayException {
    private static final long serialVersionUID = 1L;

    public TooManyRequestsException() {
        super();
    }

    public TooManyRequestsException(String message) {
        super(message);
    }

}
