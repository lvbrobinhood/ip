package batman.command;

import batman.storage.Storage;
import batman.task.Deadline;
import batman.task.TaskList;

public class DeadlineCommand extends AddTaskCommand {
    private final String description;
    private final String deadline;
    private TaskList tasks;

    public DeadlineCommand(String description, String deadline) {
        this.description = description;
        this.deadline = deadline;
    }

    @Override
    public void execute(Storage storage, TaskList tasks) {
        this.tasks = tasks;
        this.tasks.addTask(new Deadline(this.description, this.deadline));
    }

    @Override
    public String toString() {
        return super.getAddedTask(this.tasks);
    }
}