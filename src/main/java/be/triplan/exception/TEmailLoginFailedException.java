package be.triplan.exception;

public class TEmailLoginFailedException extends RuntimeException {

    public TEmailLoginFailedException() {
        super();
    }

    public TEmailLoginFailedException(String message) {
        super(message);
    }

    public TEmailLoginFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}
