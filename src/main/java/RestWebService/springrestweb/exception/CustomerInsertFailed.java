package RestWebService.springrestweb.exception;

public class CustomerInsertFailed extends RuntimeException {
    public CustomerInsertFailed(String message) {
        super(message);
    }
}