package theater.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;

/**
 * Base class for errors, that should be caught by handler in ControllerBase class
 */
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class BaseHttpException extends HttpClientErrorException {
    private Exception innerException;
    private String message;

    public Exception getInnerException() {
        return innerException;
    }

    public BaseHttpException() {
        super(HttpStatus.BAD_REQUEST, "BaseHttpException has occurred.");
    }

    public BaseHttpException(Exception innerException) {
        this();
        this.innerException = innerException;
    }

    public BaseHttpException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }

    @Override
    public String getMessage() {
        return message;
    }
}
