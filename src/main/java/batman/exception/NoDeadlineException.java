package batman.exception;

public class NoDeadlineException extends Exception {
    public NoDeadlineException() {
        super("Error: No Deadline found. Missing \"/by\" and/or deadline input in deadline task.");
    }
}