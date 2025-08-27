package batman.command;

import batman.storage.Storage;
import batman.task.Task;
import batman.task.TaskList;

public class UnmarkCommand extends Command {
    private boolean isSuccess;
    private final int index;
    private Task task;

    public UnmarkCommand(int index) {
        this.index = index;
        this.isSuccess = false;
    }

    @Override
    public void execute(Storage storage, TaskList tasks) {
        if (this.index < tasks.getSize()) {
            this.task = tasks.getTask(index);
            this.task.setUnmarked();
            this.isSuccess = true;
        }
    }

    @Override
    public String toString() {
        if (isSuccess) {
            return "OK, I've marked this task as not done yet:\n" + this.task;
        } else {
            return "Error: Index to be unmarked exceeds length of list";
        }
    }
}