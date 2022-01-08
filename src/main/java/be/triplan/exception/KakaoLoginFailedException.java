package be.triplan.exception;

public class KakaoLoginFailedException extends RuntimeException {

    public KakaoLoginFailedException() {
        super();
    }

    public KakaoLoginFailedException(String message) {
        super(message);
    }

    public KakaoLoginFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}
