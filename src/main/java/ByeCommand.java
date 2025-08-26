import java.io.IOException;

public class ByeCommand extends Command {
    @Override
    public void execute(Storage storage, TaskList tasks) {
        try {
            storage.save(tasks);
        } catch (IOException e) {
            System.out.println(String.format("Error: File writing was unsuccessful. %s",
                    e.getMessage()));
        }
    }
}