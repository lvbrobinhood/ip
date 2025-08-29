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
import batman.command.ToDoCommand;
import batman.command.UnmarkCommand;

import batman.exception.InvalidCommandException;
import batman.exception.NoDeadlineException;
import batman.exception.NoDescriptionException;
import batman.exception.NoFromToException;

public class Parser {
    public static Command parse(String input) throws NoDescriptionException, NoDeadlineException,
            NoFromToException, InvalidCommandException {
        String[] args = input.split(" ", 2);
        String command = args[0];

        switch(command) {
        case "bye":
            return new ByeCommand();

        case "list":
            return new ListCommand();

        case "mark":
            if (args.length == 2) {
                try {
                    int index = Integer.parseInt(args[1].strip()) - 1;
                    return new MarkCommand(index);
                } catch (NumberFormatException e) {
                    System.out.println("Error: Argument must be an integer");
                }
            }
            break;

        case "unmark":
            if (args.length == 2) {
                try {
                    int index = Integer.parseInt(args[1].strip()) - 1;
                    return new UnmarkCommand(index);
                } catch (NumberFormatException e) {
                    System.out.println("Error: Argument must be an integer");
                }
            }
            break;

        case "delete":
            if (args.length == 2) {
                try {
                    int index = Integer.parseInt(args[1].strip()) - 1;
                    return new DeleteCommand(index);
                } catch (NumberFormatException e) {
                    System.out.println("Error: Argument must be an integer");
                }
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
            String description = "";
            String from = "";
            String to = "";
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
                description = temp1[0];
                to = temp2[1];
                from = temp1[1].split(" /to ")[0];
            }
            return new EventCommand(description, from, to);
        }
        return null;
    }
}