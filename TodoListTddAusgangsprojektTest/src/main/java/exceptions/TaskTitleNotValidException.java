package exceptions;

public class TaskTitleNotValidException extends RuntimeException {

    public TaskTitleNotValidException() {
        super("Task title not valid, must contain at least 2 charachters!");
    }
}
