package be.triplan.exception;

/**
 *  전달한 JWT가 정상적이지 않은 경우 발생시키는 예외
 */
public class AuthenticationEntryPointException extends RuntimeException {

    public AuthenticationEntryPointException() {
        super();
    }

    public AuthenticationEntryPointException(String message) {
        super(message);
    }

    public AuthenticationEntryPointException(String message, Throwable cause) {
        super(message, cause);
    }
}
