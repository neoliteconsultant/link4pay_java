package io.link4pay.exceptions;

public class InvalidChallengeException extends Link4PayException {
    public InvalidChallengeException(String message) {
        super(message);
    }

    public InvalidChallengeException() {
        super();
    }
}
