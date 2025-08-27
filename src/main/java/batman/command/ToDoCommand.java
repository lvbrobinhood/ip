package batman.command;

import batman.storage.Storage;
import batman.task.TaskList;
import batman.task.ToDo;

public class ToDoCommand extends AddTaskCommand {
    private final String description;
    private TaskList tasks;

    public ToDoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(Storage storage, TaskList tasks) {
        this.tasks = tasks;
        this.tasks.addTask(new ToDo(this.description));
    }

    @Override
    public String toString() {
        return super.getAddedTask(this.tasks);
    }
}