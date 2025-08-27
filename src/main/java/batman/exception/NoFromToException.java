package batman.exception;

public class NoFromToException extends Exception {
    public NoFromToException() {
        super("Error: No event duration found. Missing \"/from\" and/or \"/to.\"");
    }
}