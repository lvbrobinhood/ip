package batman.command;

import batman.storage.Storage;
import batman.task.TaskList;

/**
 * Represents the command to change the date format of all timed tasks.
 * <p>
 * When executed, this command updates the date format used by all
 * {@link batman.task.TimedTask} objects in the task list. If the provided
 * format is invalid, the operation fails and an error message is returned.
 * </p>
 */
public class FormatDateCommand extends Command {
    /** Whether the date format change was successful. */
    private boolean isSuccess;

    /** The new date format pattern to apply. */
    private final String format;

    /**
     * Creates a {@code FormatDateCommand} with the given date format pattern.
     *
     * @param format the date format pattern (e.g., {@code dd/MM/yyyy})
     */
    public FormatDateCommand(String format) {
        this.format = format.strip();
        this.isSuccess = false;
    }

    /**
     * Executes the command by applying the new date format to all timed tasks.
     * <p>
     * If the provided pattern is invalid, the operation fails and
     * {@code isSuccess} is set to false.
     * </p>
     *
     * @param storage the storage handler (not used in this command)
     * @param tasks the task list containing timed tasks to update
     */
    @Override
    public void execute(Storage storage, TaskList tasks) {
        try {
            tasks.changeDateFormat(format);
            this.isSuccess = true;
        } catch (IllegalArgumentException e) {
            this.isSuccess = false;
        }
    }

    /**
     * Returns a message indicating the result of the operation.
     * <p>
     * If successful, returns a confirmation message. Otherwise,
     * returns an error message suggesting proper format usage.
     * </p>
     *
     * @return result message of the date format change
     */
    @Override
    public String toString() {
        if (isSuccess) {
            return "Date format changed successfully.";
        } else {
            return "Error: Please use a valid date format (Use MM for month, not mm)";
        }
    }
}
