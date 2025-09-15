package batman.command;

import batman.storage.Storage;
import batman.task.Task;
import batman.task.TaskList;

/**
 * Represents the command to delete a task from the task list.
 * <p>
 * When executed, this command removes the task at the specified index
 * from the task list. If the index is invalid, the deletion fails and
 * an error message is returned.
 * </p>
 */
public class DeleteCommand extends Command {
    /** Whether the deletion was successful. */
    private boolean isSuccess;

    /** Index of the task to delete (0-based). */
    private final int index;

    /** The task removed during execution, if any. */
    private Task removed;

    /** The size of the task list after deletion. */
    private int size;

    /**
     * Creates a {@code DeleteCommand} with the given index.
     *
     * @param index the index of the task to delete (0-based)
     */
    public DeleteCommand(int index) {
        this.index = index;
        this.isSuccess = false;
    }

    /**
     * Executes the command by deleting the task at the specified index.
     * <p>
     * If the index is valid, the task is removed and the success flag is set.
     * Otherwise, no task is removed and an error is recorded.
     * </p>
     *
     * @param storage the storage handler (not used in this command)
     * @param tasks the task list to delete a task from
     */
    @Override
    public void execute(Storage storage, TaskList tasks) {
        if (this.index < tasks.getSize() && this.index >= 0) {
            this.isSuccess = true;
            this.removed = tasks.deleteTask(index);
            this.size = tasks.getSize();
        }
    }

    /**
     * Returns a message indicating the result of the deletion.
     * <p>
     * If the deletion was successful, the message shows the removed task
     * and the updated task count. Otherwise, an error message is returned.
     * </p>
     *
     * @return result message of the deletion
     */
    @Override
    public String toString() {
        if (!this.isSuccess) {
            return "Error: Index to delete exceeds length of list";
        }
        return "Noted. I've removed this task:\n" + this.removed
                + String.format("\nNow you have %d tasks in the list.", this.size);
    }
}
