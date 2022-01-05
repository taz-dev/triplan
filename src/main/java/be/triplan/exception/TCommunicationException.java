package be.triplan.exception;

public class TCommunicationException extends RuntimeException {

    public TCommunicationException() {
        super();
    }

    public TCommunicationException(String message) {
        super(message);
    }

    public TCommunicationException(String message, Throwable cause) {
        super(message, cause);
    }
}
