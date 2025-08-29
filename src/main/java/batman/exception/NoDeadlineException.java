package batman.exception;

/**
 * Thrown to indicate that a deadline task is missing the required
 * {@code /by} keyword and/or the deadline date input.
 */
public class NoDeadlineException extends Exception {
    /**
     * Creates a new {@code NoDeadlineException} with a default error message.
     */
    public NoDeadlineException() {
        super("Error: No Deadline found. Missing \"/by\" and/or deadline input in deadline task.");
    }
}
