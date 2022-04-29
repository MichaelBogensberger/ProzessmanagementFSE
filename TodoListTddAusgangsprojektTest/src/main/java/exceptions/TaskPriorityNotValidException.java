package exceptions;

public class TaskPriorityNotValidException extends RuntimeException {
    public TaskPriorityNotValidException() {
        super("Task priority not valid, must be between 1 und 10");
    }
}
