package theater.domain.exceptions;

public class BaseRuntimeException extends RuntimeException {
    protected final String message;

    @Override
    public String getMessage() {
        return message;
    }

    BaseRuntimeException(String message) {
        this.message = message;
    }
}
