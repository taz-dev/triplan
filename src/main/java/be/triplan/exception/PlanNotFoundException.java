package be.triplan.exception;

public class PlanNotFoundException extends RuntimeException {

    public PlanNotFoundException() {
        super();
    }

    public PlanNotFoundException(String message) {
        super(message);
    }

    public PlanNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
