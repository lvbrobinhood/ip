package batman.command;

import batman.storage.Storage;
import batman.task.Task;
import batman.task.TaskList;

public class MarkCommand extends Command {
    private boolean isSuccess;
    private final int index;
    private Task task;

    public MarkCommand(int index) {
        this.index = index;
        this.isSuccess = false;
    }

    @Override
    public void execute(Storage storage, TaskList tasks) {
        if (this.index < tasks.getSize()) {
            this.task = tasks.getTask(index);
            this.task.setMarked();
            this.isSuccess = true;
        }
    }

    @Override
    public String toString() {
        if (isSuccess) {
            return "Nice! I've marked this task as done:\n" + this.task;
        } else {
            return "Error: Index to be marked exceeds length of list";
        }
    }
}