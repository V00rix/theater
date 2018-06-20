package theater.domain.exceptions;

public class NotImplementedException extends BaseRuntimeException {
    public NotImplementedException() {
        super("Functionality is not yet implemented.");
        System.out.println(this.message);
    }
}
