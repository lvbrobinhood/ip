package batman.core;

import batman.command.ByeCommand;
import batman.command.Command;

import batman.exception.InvalidCommandException;
import batman.exception.NoDeadlineException;
import batman.exception.NoDescriptionException;
import batman.exception.NoFromToException;

import batman.storage.Storage;

import batman.task.TaskList;

import batman.ui.Parser;
import batman.ui.Ui;

import java.time.format.DateTimeParseException;

import java.util.Scanner;

/**
 * The main entry point of the Batman task manager application.
 * <p>
 * This class initializes the task list, storage, and user interface,
 * and manages the program flow by continuously reading user input,
 * parsing it into commands, and executing them until termination.
 * </p>
 */
public class Batman {
    /** The list of tasks currently being managed. */
    private TaskList tasks;

    /** Handles loading and saving of tasks to persistent storage. */
    private Storage storage;

    /** Handles user interface operations such as displaying messages. */
    private Ui ui;

    /**
     * Creates a new {@code Batman} application with the given storage configuration.
     *
     * @param directory the directory path where the task file is stored
     * @param fileName the file name of the task storage file
     */
    public Batman(String directory, String fileName) {
        this.tasks = new TaskList();
        this.storage = new Storage(directory, fileName);
        this.ui = new Ui();
    }

    /**
     * Runs the main loop of the application.
     * <p>
     * Loads tasks from storage, prints a welcome message,
     * and repeatedly reads user input until the {@code bye} command is given.
     * Commands are parsed and executed, with errors handled gracefully.
     * </p>
     */
    public void run() {
        Scanner sc = new Scanner(System.in);
        storage.load(tasks);
        this.ui.printWelcomeMsg();

        while (true) {
            String input = sc.nextLine();
            try {
                Command currCommand = Parser.parse(input);
                if (currCommand == null) {
                    throw new InvalidCommandException();
                }

                currCommand.execute(storage, tasks);
                ui.printCommand(currCommand);

                if (currCommand instanceof ByeCommand) {
                    break;
                }
            } catch (NoDescriptionException | InvalidCommandException | NoDeadlineException | NoFromToException e) {
                System.out.println(e.getMessage());
            } catch (DateTimeParseException e) {
                System.out.println("Error: Please use yyyy-mm-dd format for time");
            }
        }
        sc.close();
    }

    public void initApp() {
        storage.load(tasks);
    }

    public String processInput(String input) {
        try {
            Command currCommand = Parser.parse(input);
            if (currCommand == null) {
                throw new InvalidCommandException();
            }

            currCommand.execute(storage, tasks);

            if (currCommand instanceof ByeCommand) {
                return "Bye! See you soon!";
            }

            return currCommand.toString();

        } catch (NoDescriptionException | InvalidCommandException |
                 NoDeadlineException | NoFromToException e) {
            return e.getMessage();
        } catch (DateTimeParseException e) {
            return "Error: Please use yyyy-mm-dd format for time";
        }
    }

    public boolean isExitCommand(String input) {
        try {
            return Parser.parse(input) instanceof ByeCommand;
        } catch (NoDescriptionException | NoDeadlineException |
                NoFromToException| InvalidCommandException e) {
            return false;
        }
    }


    /**
     * The main entry point of the program.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        new Batman("./data", "tasks.csv").run();
    }
}
