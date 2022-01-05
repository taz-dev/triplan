package be.triplan.exception;

public class TRefreshTokenException extends RuntimeException {

    public TRefreshTokenException() {
        super();
    }

    public TRefreshTokenException(String message) {
        super(message);
    }

    public TRefreshTokenException(String message, Throwable cause) {
        super(message, cause);
    }

}
