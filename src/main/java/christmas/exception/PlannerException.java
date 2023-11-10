package christmas.exception;

public class PlannerException extends IllegalArgumentException {
    private static final String ERROR_PREFIX = "[ERROR] ";

    public PlannerException(ExceptionMessage message) {
        super(ERROR_PREFIX + message.toString());
    }
}
