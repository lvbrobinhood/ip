package batman.command;

import batman.storage.Storage;
import batman.task.Task;
import batman.task.TaskList;

/**
 * Represents the command to mark a task as done.
 * <p>
 * When executed, this command sets the specified task's status
 * to done. If the given index is invalid, the operation fails
 * and an error message is returned.
 * </p>
 */
public class MarkCommand extends Command {
    /** Whether the mark operation was successful. */
    private boolean isSuccess;

    /** Index of the task to be marked as done (0-based). */
    private final int index;

    /** The task that was marked as done, if successful. */
    private Task task;

    /**
     * Creates a {@code MarkCommand} with the given index.
     *
     * @param index the index of the task to mark as done (0-based)
     */
    public MarkCommand(int index) {
        this.index = index;
        this.isSuccess = false;
    }

    /**
     * Executes the command by marking the task at the given index as done.
     * <p>
     * If the index is valid, the task is updated and the success flag is set.
     * Otherwise, the operation fails and no task is updated.
     * </p>
     *
     * @param storage the storage handler (not used in this command)
     * @param tasks the task list containing the task to be marked
     */
    @Override
    public void execute(Storage storage, TaskList tasks) {
        if (this.index < tasks.getSize() && this.index >= 0) {
            this.task = tasks.getTask(index);
            this.task.setMarked();
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
     * @return result message of the mark operation
     */
    @Override
    public String toString() {
        if (isSuccess) {
            return "Nice! I've marked this task as done:\n" + this.task;
        } else {
            return "Error: Index to be marked exceeds length of list";
        }
    }
}
