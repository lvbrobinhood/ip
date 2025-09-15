package batman.command;

import batman.storage.Storage;
import batman.task.Deadline;
import batman.task.Task;
import batman.task.TaskList;

public class SnoozeCommand extends Command {
    private boolean isSuccess;
    private final int index;
    private final String deadline;
    private Deadline snoozed;

    public SnoozeCommand(int index, String deadline) {
        this.index = index - 1;
        this.deadline = deadline;
        this.isSuccess = false;
    }

    @Override
    public void execute(Storage storage, TaskList tasks) {
        if (this.index < tasks.getSize() && this.index >= 0
                && tasks.getTask(this.index) instanceof Deadline) {
            this.snoozed = (Deadline) tasks.getTask(this.index);
            this.snoozed.setDeadline(this.deadline);
            this.isSuccess = true;
        }
    }

    @Override
    public String toString() {
        if (!this.isSuccess) {
            return "Error: Unable to snooze task. Either index out of range or task is not of Deadline type.";
        }
        return "Noted. I've snoozed this task:\n" + this.snoozed;
    }
}
