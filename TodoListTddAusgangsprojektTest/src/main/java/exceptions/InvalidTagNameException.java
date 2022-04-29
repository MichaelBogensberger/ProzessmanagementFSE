package exceptions;

public class InvalidTagNameException extends RuntimeException {

    public InvalidTagNameException() {
        super("Invalid tagname!");
    }
}
