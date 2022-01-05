package be.triplan.exception;

/**
 *  이미 가입된 member가 있을 경우 발생시키는 예외
 */
public class TUserExistException extends RuntimeException {

    public TUserExistException() {
        super();
    }

    public TUserExistException(String message) {
        super(message);
    }

    public TUserExistException(String message, Throwable cause) {
        super(message, cause);
    }
}
