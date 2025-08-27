package batman.command;

import batman.storage.Storage;
import batman.task.TaskList;

import java.io.IOException;

public class ByeCommand extends Command {
    private String message;

    @Override
    public void execute(Storage storage, TaskList tasks) {
        try {
            this.message = storage.save(tasks);
        } catch (IOException e) {
            this.message = String.format("Error: File writing was unsuccessful. %s", e.getMessage());
        }
    }

    @Override
    public String toString() {
        return this.message;
    }
}