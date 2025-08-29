package batman.command;

import batman.storage.Storage;
import batman.task.TaskList;
import batman.task.ToDo;

/**
 * Represents the command to add a {@link ToDo} task.
 * <p>
 * When executed, this command creates a new to-do task with the
 * specified description and adds it to the task list.
 * </p>
 */
public class ToDoCommand extends AddTaskCommand {
    /** Description of the to-do task. */
    private final String description;

    /** The task list reference updated during execution. */
    private TaskList tasks;

    /**
     * Creates a {@code ToDoCommand} with the given description.
     *
     * @param description description of the to-do task
     */
    public ToDoCommand(String description) {
        this.description = description;
    }

    /**
     * Executes the command by adding a new to-do task to the list.
     *
     * @param storage the storage handler (not used in this command)
     * @param tasks the task list to add the to-do task to
     */
    @Override
    public void execute(Storage storage, TaskList tasks) {
        this.tasks = tasks;
        this.tasks.addTask(new ToDo(this.description));
    }

    /**
     * Returns a confirmation message indicating the task was added.
     *
     * @return confirmation message with the added task details
     */
    @Override
    public String toString() {
        return super.getAddedTask(this.tasks);
    }
}
