package be.triplan.exception;

/**
 *  전달한 JWT가 정상적이지 않은 경우 발생시키는 예외
 */
public class TAuthenticationEntryPointException extends RuntimeException {

    public TAuthenticationEntryPointException() {
        super();
    }

    public TAuthenticationEntryPointException(String message) {
        super(message);
    }

    public TAuthenticationEntryPointException(String message, Throwable cause) {
        super(message, cause);
    }
}
