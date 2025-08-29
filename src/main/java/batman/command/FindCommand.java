package batman.command;

import batman.storage.Storage;
import batman.task.TaskList;

public class FindCommand extends Command {
    private final String keyword;
    private TaskList tasks;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(Storage storage, TaskList tasks) {
        this.tasks = tasks.filterTasks(keyword);
    }

    @Override
    public String toString() {
        if (this.tasks.getSize() == 0) {
            return "No tasks found";
        }
        return "Here are the matching tasks in your list:\n" + this.tasks.toString();
    }
}