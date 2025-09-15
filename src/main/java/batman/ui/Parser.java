package batman.ui;

import batman.command.ByeCommand;
import batman.command.Command;
import batman.command.DeadlineCommand;
import batman.command.DeleteCommand;
import batman.command.EventCommand;
import batman.command.FindCommand;
import batman.command.FormatDateCommand;
import batman.command.ListCommand;
import batman.command.MarkCommand;
import batman.command.SnoozeCommand;
import batman.command.ToDoCommand;
import batman.command.UnmarkCommand;

import batman.exception.InvalidCommandException;
import batman.exception.NoDeadlineException;
import batman.exception.NoDescriptionException;
import batman.exception.NoFromToException;

/**
 * Parses user input and converts it into the corresponding {@link Command}.
 * <p>
 * This class is responsible for interpreting user commands such as
 * {@code todo}, {@code deadline}, {@code event}, {@code mark}, etc.,
 * and returning the appropriate {@link Command} object to be executed.
 * </p>
 */
public class Parser {
    private final static String MARK_COMMAND = "mark";
    private final static String UNMARK_COMMAND = "unmark";
    private final static String DELETE_COMMAND = "delete";

    /**
     * Parses the given user input and returns the corresponding command.
     *
     * @param input the raw user input string
     * @return the corresponding {@link Command}, or {@code null} if input is invalid
     * @throws NoDescriptionException if a task description is missing or empty
     * @throws NoDeadlineException if a deadline command is missing {@code /by} or a valid date
     * @throws NoFromToException if an event command is missing {@code /from}, {@code /to}, or valid dates
     * @throws InvalidCommandException if a command is invalid (e.g., empty or invalid find command)
     */
    public static Command parse(String input)
            throws NoDescriptionException, NoDeadlineException,
            NoFromToException, InvalidCommandException {
        String[] args = input.split(" ", 2);
        String command = args[0];

        switch(command) {
        case "bye":
            return new ByeCommand();

        case "list":
            return new ListCommand();

        case MARK_COMMAND, DELETE_COMMAND, UNMARK_COMMAND:
            return checkValidIndex(args);

        case "snooze":
            if (args.length == 2 && (!args[1].isBlank()) && !args[1].strip().startsWith("/by")) {
                String[] temp = args[1].strip().split(" /by ", 2);
                if (temp.length != 2 || temp[1].isBlank()) {
                    throw new NoDeadlineException();
                }

                try {
                    int index = Integer.parseInt(temp[0].strip());
                    return new SnoozeCommand(index, temp[1].strip());
                } catch (NumberFormatException e) {
                    System.out.println("Error: Argument must be an integer");
                }
            } else {
                // handle case where no index present
                throw new InvalidCommandException();
            }
            break;

        case "formatdate":
            if (args.length == 2) {
                return new FormatDateCommand(args[1]);
            }
            break;

        case "find":
            if (args.length == 2) {
                if (!args[1].isBlank()) {
                    return new FindCommand(args[1]);
                } else {
                    throw new InvalidCommandException();
                }
            }
            break;

        case "todo":
            if (args.length == 2 && (!args[1].isBlank())) {
                return new ToDoCommand(args[1].strip());
            } else {
                throw new NoDescriptionException();
            }

        case "deadline":
            if (args.length == 2 && (!args[1].isBlank()) && !args[1].strip().startsWith("/by")) {
                String[] temp = args[1].strip().split(" /by ", 2);
                if (temp.length != 2 || temp[1].isBlank()) {
                    throw new NoDeadlineException();
                }
                return new DeadlineCommand(temp[0].strip(), temp[1].strip());
            } else {
                throw new NoDescriptionException();
            }

        case "event":
            if (!args[1].contains(" /from ") || !args[1].contains(" /to ")) {
                throw new NoFromToException();
            } else {
                String[] temp1 = args[1].strip().split(" /from ", 2);
                String[] temp2 = args[1].strip().split(" /to ", 2);
                if (temp1[0].isBlank()) {
                    throw new NoDescriptionException();
                } else if (temp2.length != 2 || temp2[1].isBlank()) {
                    throw new NoFromToException();
                } else if (temp1[1].split(" /to ").length != 2 || temp1[1].split(" /to ")[0].isBlank()) {
                    throw new NoFromToException();
                }
                String description = temp1[0];
                String to = temp2[1];
                String from = temp1[1].split(" /to ")[0];
                return new EventCommand(description, from, to);
            }
        }
        return null;
    }

    public static Command checkValidIndex(String[] args) {
        String commandType = args[0];
        if (args.length == 2) {
            try {
                int index = Integer.parseInt(args[1].strip()) - 1;
                switch (commandType) {
                case MARK_COMMAND:
                    return new MarkCommand(index);
                case UNMARK_COMMAND:
                    return new UnmarkCommand(index);
                case DELETE_COMMAND:
                    return new DeleteCommand(index);
                default:
                    return null;
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Argument must be an integer");
            }
        }
        return null;
    }
}