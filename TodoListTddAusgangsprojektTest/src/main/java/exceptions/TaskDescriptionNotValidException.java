package exceptions;

public class TaskDescriptionNotValidException extends RuntimeException {
    public TaskDescriptionNotValidException() {
        super("Task description not valid. Must contain at least one character!");
    }
}
