package batman.command;

import batman.storage.Storage;
import batman.task.TaskList;

/**
 * Represents the command to list all tasks in the task list.
 * <p>
 * When executed, this command retrieves the current tasks without making
 * any modifications, and displays them in a formatted string.
 * </p>
 */
public class ListCommand extends Command {
    /** The task list reference updated during execution. */
    private TaskList tasks;

    /**
     * Executes the command by setting the current task list reference.
     *
     * @param storage the storage handler (not used in this command)
     * @param tasks the task list to be displayed
     */
    @Override
    public void execute(Storage storage, TaskList tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns a string representation of the task list.
     *
     * @return string representation of all tasks
     */
    @Override
    public String toString() {
        return tasks.toString();
    }
}
