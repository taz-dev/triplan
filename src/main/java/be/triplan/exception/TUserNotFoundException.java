package be.triplan.exception;

public class TUserNotFoundException extends RuntimeException {

    public TUserNotFoundException() {
        super();
    }

    public TUserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public TUserNotFoundException(String message) {
        super(message);
    }
}
