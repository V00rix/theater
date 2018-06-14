package theater.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


/**
 * Base class for errors, that should be caught by handler in ControllerBase class
 */
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class BaseHttpException extends BaseRuntimeException {
    private Exception innerException;
    private String message;

    public Exception getInnerException() {
        return innerException;
    }

    public BaseHttpException() {
    }

    public BaseHttpException(Exception innerException) {
        this.innerException = innerException;
    }

    public BaseHttpException(String message) {
    }

    @Override
    public String getMessage() {
        return message;
    }
}
