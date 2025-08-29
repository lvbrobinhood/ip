package batman.exception;

/**
 * Thrown to indicate that an event task is missing the required
 * {@code /from} and/or {@code /to} fields for its duration.
 */
public class NoFromToException extends Exception {
    /**
     * Creates a new {@code NoFromToException} with a default error message.
     */
    public NoFromToException() {
        super("Error: No event duration found. Missing \"/from\" and/or \"/to.\"");
    }
}
