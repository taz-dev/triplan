package be.triplan.exception;

/**
 *  이미 사용중인 nameTag 가 있을 경우 발생시키는 예외
 */
public class NameTagAlreadyInUseException extends RuntimeException {

    public NameTagAlreadyInUseException() {
        super();
    }

    public NameTagAlreadyInUseException(String message) {
        super(message);
    }

    public NameTagAlreadyInUseException(String message, Throwable cause) {
        super(message, cause);
    }
}
