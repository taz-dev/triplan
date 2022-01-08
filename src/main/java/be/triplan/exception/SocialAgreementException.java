package be.triplan.exception;

public class SocialAgreementException extends RuntimeException{

    public SocialAgreementException() {
        super();
    }

    public SocialAgreementException(String message) {
        super(message);
    }

    public SocialAgreementException(String message, Throwable cause) {
        super(message, cause);
    }
}
