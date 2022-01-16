package be.triplan.exception;

/**
 *  이미 가입된 member 가 있을 경우 발생시키는 예외
 */
public class UserExistException extends RuntimeException {

    public UserExistException() {
        super();
    }

    public UserExistException(String message) {
        super(message);
    }

    public UserExistException(String message, Throwable cause) {
        super(message, cause);
    }
}
