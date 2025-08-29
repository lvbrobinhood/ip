package batman.command;

import batman.storage.Storage;
import batman.task.Task;
import batman.task.TaskList;

/**
 * Represents the command to unmark a task (set it as not done).
 * <p>
 * When executed, this command sets the specified task's status
 * to not done. If the given index is invalid, the operation fails
 * and an error message is returned.
 * </p>
 */
public class UnmarkCommand extends Command {
    /** Whether the unmark operation was successful. */
    private boolean isSuccess;

    /** Index of the task to be unmarked (0-based). */
    private final int index;

    /** The task that was unmarked, if successful. */
    private Task task;

    /**
     * Creates an {@code UnmarkCommand} with the given index.
     *
     * @param index the index of the task to unmark (0-based)
     */
    public UnmarkCommand(int index) {
        this.index = index;
        this.isSuccess = false;
    }

    /**
     * Executes the command by unmarking the task at the given index.
     * <p>
     * If the index is valid, the task is updated and the success flag is set.
     * Otherwise, the operation fails and no task is updated.
     * </p>
     *
     * @param storage the storage handler (not used in this command)
     * @param tasks the task list containing the task to be unmarked
     */
    @Override
    public void execute(Storage storage, TaskList tasks) {
        if (this.index < tasks.getSize()) {
            this.task = tasks.getTask(index);
            this.task.setUnmarked();
            this.isSuccess = true;
        }
    }

    /**
     * Returns a message indicating the result of the operation.
     * <p>
     * If successful, returns a confirmation message with the updated task.
     * Otherwise, returns an error message.
     * </p>
     *
     * @return result message of the unmark operation
     */
    @Override
    public String toString() {
        if (isSuccess) {
            return "OK, I've marked this task as not done yet:\n" + this.task;
        } else {
            return "Error: Index to be unmarked exceeds length of list";
        }
    }
}
