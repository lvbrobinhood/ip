import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Batman {
    private static final String FILE_DIRECTORY = "./data";
    private static final String FILE_NAME = "tasks.csv";
    public static final String line = "_____________________________________________________\n";

    public static void main(String[] args) {
        //String FILE = FILE_DIRECTORY + "/" + FILE_NAME;
        Scanner sc = new Scanner(System.in);
        TaskList tasks = new TaskList();
        Storage storage = new Storage(FILE_DIRECTORY, FILE_NAME);
        storage.load(tasks);
        System.out.println(Batman.line + "Hello! I'm Batman.\n" + "What can I do for you?\n" + Batman.line);

        while (true) {
            String input = sc.nextLine();
            try {
                Command currCommand = Parser.parse(input);
                if (currCommand == null) {
                    throw new InvalidCommandException();
                }

                currCommand.execute(storage, tasks);

                if (currCommand instanceof ByeCommand) {
                    break;
                }
            } catch (NoDescriptionException | InvalidCommandException | NoDeadlineException | NoFromToException e) {
                System.out.println(e.getMessage());
            } catch (DateTimeParseException e) {
                System.out.println("Error: Please use yyyy-mm-dd format for time");
            }


            /*if (input.equals("bye")) {
                try {
                    storage.save(tasks);
                } catch (IOException e) {
                    System.out.println(String.format("Error: File writing was unsuccessful. %s",
                            e.getMessage()));
                }

                System.out.println(Batman.line + "Bye. Hope to see you again soon!\n" + Batman.line);
                break;

            } else if (input.equals("list")) {
                tasks.printTaskList();

            } else if (input.startsWith("mark") && input.length() >= 6) {
                String substr = input.substring(5);
                try {
                    int index = Integer.parseInt(substr) - 1;
                    tasks.getTask(index).setMarked();
                } catch (NumberFormatException e) {
                    System.out.println("Error: Argument must be an integer");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Error: Index to be marked exceeds length of list");
                }

            } else if (input.startsWith("unmark") && input.length() >= 8) {
                String substr = input.substring(7);
                try {
                    int index = Integer.parseInt(substr) - 1;
                    tasks.getTask(index).setUnmarked();
                } catch (NumberFormatException e) {
                    System.out.println("Error: Argument must be an integer");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Error: Index to be unmarked exceeds length of list");
                }

            } else if (input.startsWith("delete") && input.length() >= 8) {
                String substr = input.substring(7);
                try {
                    int index = Integer.parseInt(substr) - 1;
                    tasks.deleteTask(index);
                } catch (NumberFormatException e) {
                    System.out.println("Error: Argument must be an integer");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Error: Index to delete from exceeds length of list");
                }

            } else if (input.startsWith("formatdate ") && input.length() >= 12) {
                try {
                    String substr = input.substring(11);
                    tasks.changeDateFormat(substr.strip());
                } catch (IllegalArgumentException e) {
                    System.out.println("Error: Please use a valid date format (Use MM for month, not mm)");
                }
            } else {
                try {
                    tasks.addTask(input);
                } catch (NoDescriptionException | InvalidCommandException | NoDeadlineException | NoFromToException e) {
                    System.out.println(e.getMessage());
                } catch (DateTimeParseException e) {
                    System.out.println("Error: Please use yyyy-mm-dd format for time");
                }
            }*/
        }

        sc.close();
    }
}
