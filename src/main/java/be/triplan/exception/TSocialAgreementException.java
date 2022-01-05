package be.triplan.exception;

public class TSocialAgreementException extends RuntimeException{

    public TSocialAgreementException() {
        super();
    }

    public TSocialAgreementException(String message) {
        super(message);
    }

    public TSocialAgreementException(String message, Throwable cause) {
        super(message, cause);
    }
}
