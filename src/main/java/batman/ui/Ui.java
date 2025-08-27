package batman.ui;

import batman.command.Command;

public class Ui {
    public final String line = "_____________________________________________________";

    private void template(String message) {
        System.out.println(this.line);
        System.out.println(message);
        System.out.println(this.line);
    }

    public void printWelcomeMsg() {
        this.template("Hello! I'm Batman.\n" + "What can I do for you?");
    }

    public void printCommand(Command command) {
        this.template(command.toString());
    }
}