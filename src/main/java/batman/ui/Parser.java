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
 * {@code todo}, {@code deadline}, {@code event}, {@code mark}, etc. and
 * returning the appropriate {@link Command} object to be executed.
 * </p>
 */
public class Parser {

    /**
     * Parses the given user input and returns the corresponding command.
     * <p>
     * The input string is split, and based on the command type (e.g., "todo", "deadline", "event"),
     * the appropriate {@link Command} object is created and returned. If the input is invalid or
     * if required arguments are missing, an exception is thrown.
     * </p>
     *
     * @param input the raw user input string
     * @return the corresponding {@link Command}, or {@code null} if input is invalid
     * @throws NoDescriptionException if a task description is missing or empty
     * @throws NoDeadlineException if a deadline command is missing {@code /by} or a valid date
     * @throws NoFromToException if an event command is missing {@code /from}, {@code /to}, or valid dates
     * @throws InvalidCommandException if a command is invalid (e.g., empty or invalid find command)
     */
    public static Command parse(String input)
            throws NoDescriptionException, NoDeadlineException, NoFromToException, InvalidCommandException {
        String[] args = input.split(" ", 2);
        CommandType currType;
        try {
            currType = CommandType.valueOf(args[0].strip().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidCommandException();
        }

        switch(currType) {
        case BYE:
            return new ByeCommand();

        case LIST:
            return new ListCommand();

        case MARK, DELETE, UNMARK:
            return checkValidIndex(args);

        case FORMATDATE:
            if (args.length == 2) {
                return new FormatDateCommand(args[1]);
            }
            break;

        case FIND:
            if (args.length == 2) {
                if (args[1].isBlank()) {
                    throw new InvalidCommandException();
                }
                return new FindCommand(args[1]);
            }
            break;

        case TODO:
            if (args.length == 2 && (!args[1].isBlank())) {
                return new ToDoCommand(args[1].strip());
            } else {
                throw new NoDescriptionException();
            }

        case SNOOZE, DEADLINE:
            return checkValidDeadlineSnooze(args);

        case EVENT:
            return checkValidEvent(args);
        }
        return null;
    }

    /**
     * Checks if the index for the command is valid (e.g., mark, unmark, delete).
     * <p>
     * This method ensures that the index is an integer and falls within the valid range of tasks.
     * </p>
     *
     * @param args the command arguments
     * @return the corresponding {@link Command} for the valid index, or {@code null} if the index is invalid
     */
    public static Command checkValidIndex(String[] args) {
        CommandType currType = CommandType.valueOf(args[0].strip().toUpperCase());
        if (args.length == 2) {
            try {
                int index = Integer.parseInt(args[1].strip()) - 1;
                switch (currType) {
                case MARK:
                    return new MarkCommand(index);
                case UNMARK:
                    return new UnmarkCommand(index);
                case DELETE:
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

    /**
     * Checks if the deadline or snooze command is valid.
     * <p>
     * Validates that the task description and deadline (if applicable) are provided correctly.
     * </p>
     *
     * @param args the command arguments
     * @return the corresponding {@link Command} for a valid deadline or snooze
     * @throws NoDeadlineException if the deadline is missing or invalid
     * @throws InvalidCommandException if the snooze command is missing an index
     * @throws NoDescriptionException if a description is missing for the deadline
     */
    public static Command checkValidDeadlineSnooze(String[] args)
            throws NoDeadlineException, InvalidCommandException, NoDescriptionException {
        CommandType currType = CommandType.valueOf(args[0].strip().toUpperCase());
        if (args.length == 2 && (!args[1].isBlank()) && !args[1].strip().startsWith("/by")) {
            String[] temp = args[1].strip().split(" /by ", 2);
            if (temp.length != 2 || temp[1].isBlank()) {
                throw new NoDeadlineException();
            }

            switch (currType) {
            case DEADLINE:
                return new DeadlineCommand(temp[0].strip(), temp[1].strip());
            case SNOOZE:
                try {
                    int index = Integer.parseInt(temp[0].strip());
                    return new SnoozeCommand(index, temp[1].strip());
                } catch (NumberFormatException e) {
                    System.out.println("Error: Argument must be an integer");
                }
            }

        } else {
            switch (currType) {
            case SNOOZE:
                throw new InvalidCommandException();
            case DEADLINE:
                throw new NoDescriptionException();
            }
        }
        return null;
    }

    /**
     * Checks if the event command is valid.
     * <p>
     * Validates that both {@code /from} and {@code /to} arguments are present with valid descriptions and dates.
     * </p>
     *
     * @param args the command arguments
     * @return the corresponding {@link Command} for a valid event
     * @throws NoFromToException if {@code /from} or {@code /to} is missing or invalid
     * @throws NoDescriptionException if the description of the event is missing
     */
    public static Command checkValidEvent(String[] args) throws NoFromToException, NoDescriptionException {
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
}
