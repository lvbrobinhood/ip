package batman.exception;

/**
 * Thrown to indicate that a user has entered an invalid command.
 */
public class InvalidCommandException extends Exception {
    /**
     * Creates a new {@code InvalidCommandException} with a default error message.
     */
    public InvalidCommandException() {
        super("Error: Invalid Command.");
    }
}
