package batman.ui;

import batman.command.Command;

/**
 * Handles user interface operations such as displaying messages.
 * <p>
 * Provides helper methods for printing formatted output with
 * separators, including welcome messages and command results.
 * </p>
 */
public class Ui {
    /** Separator line used for formatting messages. */
    private final String LINE = "_____________________________________________________";

    /**
     * Prints the welcome message shown when the program starts.
     */
    public void printWelcomeMsg() {
        this.template("Hello! I'm Batman.\n" + "What can I do for you?");
    }

    /**
     * Prints the result of executing a command.
     *
     * @param command the command whose output will be displayed
     */
    public void printCommand(Command command) {
        this.template(command.toString());
    }

    /**
     * Prints a message wrapped with separator lines.
     *
     * @param message the message to display
     */
    private void template(String message) {
        System.out.println(this.LINE);
        System.out.println(message);
        System.out.println(this.LINE);
    }
}
