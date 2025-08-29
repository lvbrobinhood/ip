package batman.exception;

/**
 * Thrown to indicate that a task description is missing or empty.
 */
public class NoDescriptionException extends Exception {
    /**
     * Creates a new {@code NoDescriptionException} with a default error message.
     */
    public NoDescriptionException() {
        super("Error: No Description found.");
    }
}
