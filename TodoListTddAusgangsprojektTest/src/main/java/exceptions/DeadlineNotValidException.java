package exceptions;

public class DeadlineNotValidException extends RuntimeException {
    public DeadlineNotValidException() {
        super("Invalid deadline!");
    }
}
