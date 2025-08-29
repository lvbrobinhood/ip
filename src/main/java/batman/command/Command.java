package batman.command;

import batman.storage.Storage;
import batman.task.TaskList;

/**
 * Represents a user command that can be executed.
 * <p>
 * All specific command types (e.g., add, delete, mark) should extend this class
 * and implement the {@link #execute(Storage, TaskList)} method to define
 * their behaviour.
 * </p>
 */
public abstract class Command {
    /**
     * Executes the command with the given storage and task list.
     *
     * @param storage the storage handler for saving/loading tasks
     * @param tasks the task list on which the command will operate
     */
    public abstract void execute(Storage storage, TaskList tasks);
}
