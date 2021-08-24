package io.link4pay.exceptions;

public class TimeoutException extends Link4PayException {
    private static final long serialVersionUID = 1L;

    public TimeoutException(String message) {
        super(message);
    }

    public TimeoutException(String message, Throwable cause) {
        super(message, cause);
    }
}
