package batman.command;

import batman.storage.Storage;
import batman.task.Deadline;
import batman.task.Task;
import batman.task.TaskList;

/**
 * Command to snooze a task by updating its deadline.
 * <p>
 * This command allows the user to update the deadline of a task, specifically tasks
 * of type {@link Deadline}. The command checks whether the task exists and if it's a
 * {@code Deadline} task before updating its deadline.
 * </p>
 */
public class SnoozeCommand extends Command {
    private boolean isSuccess;
    private final int index;
    private final String deadline;
    private Deadline snoozed;

    /**
     * Creates a new {@code SnoozeCommand} with the specified task index and new deadline.
     * <p>
     * The index is adjusted to zero-based indexing, and the new deadline is stored as a string.
     * </p>
     *
     * @param index the one-based index of the task to snooze
     * @param deadline the new deadline as a string (e.g., "yyyy-mm-dd")
     */
    public SnoozeCommand(int index, String deadline) {
        this.index = index - 1;  // Adjust to zero-based index
        this.deadline = deadline;
        this.isSuccess = false;
    }

    /**
     * Executes the command by snoozing the task at the specified index.
     * <p>
     * If the task at the given index is a {@link Deadline} task, its deadline is updated
     * with the new value. If the index is invalid or the task is not a {@code Deadline},
     * the operation fails.
     * </p>
     *
     * @param storage the storage object to access the task list (not used in this method)
     * @param tasks the list of tasks to operate on
     */
    @Override
    public void execute(Storage storage, TaskList tasks) {
        if (this.index < tasks.getSize() && this.index >= 0
                && tasks.getTask(this.index) instanceof Deadline) {
            this.snoozed = (Deadline) tasks.getTask(this.index);
            this.snoozed.setDeadline(this.deadline);
            this.isSuccess = true;
        }
    }

    /**
     * Returns a string representation of the result of the snooze operation.
     * <p>
     * If the snooze operation was successful, it returns a confirmation message along with the
     * updated task. If the operation failed, it returns an error message.
     * </p>
     *
     * @return a string indicating whether the snooze operation was successful or not
     */
    @Override
    public String toString() {
        if (!this.isSuccess) {
            return "Error: Unable to snooze task. Either index out of range or task is not of Deadline type.";
        }
        return "Noted. I've snoozed this task:\n" + this.snoozed;
    }
}
