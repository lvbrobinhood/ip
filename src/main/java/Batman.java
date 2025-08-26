import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Batman {
    private TaskList tasks;
    private Storage storage;
    private Ui ui;

    public Batman(String directory, String fileName) {
        this.tasks = new TaskList();
        this.storage = new Storage(directory, fileName);
        this.ui = new Ui();
    }

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

    public static void main(String[] args) {
        new Batman("./data", "tasks.csv").run();
    }
}
