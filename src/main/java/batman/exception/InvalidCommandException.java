package batman.exception;

public class InvalidCommandException extends Exception {
    public InvalidCommandException() {
        super("Error: Invalid Command.");
    }
}