package theater.domain.exceptions;

public class NotImplementedException extends BaseRuntimeException {
    public NotImplementedException() {
        System.out.println("Functionality is not yet implemented.");
    }
}
