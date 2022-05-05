package RestWebService.springrestweb.exception;

public class todoInsertFailed extends RuntimeException {
    public todoInsertFailed(String message) {
        super(message);
    }
}