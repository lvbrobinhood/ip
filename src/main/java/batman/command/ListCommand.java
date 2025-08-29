package batman.command;

import batman.storage.Storage;
import batman.task.TaskList;

public class ListCommand extends Command {
    private TaskList tasks;

    @Override
    public void execute(Storage storage, TaskList tasks) {
        this.tasks = tasks;
    }

    @Override
    public String toString() {
        return "Here are the tasks in your list:\n" + tasks.toString();
    }
}