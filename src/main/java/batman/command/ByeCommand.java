package batman.command;

import batman.storage.Storage;
import batman.task.TaskList;

import java.io.IOException;

/**
 * Represents the command to exit the program.
 * <p>
 * When executed, this command saves the current task list to storage
 * before termination. If saving fails, an error message is stored instead.
 * </p>
 */
public class ByeCommand extends Command {
    /** Message to be shown after execution, indicating success or error. */
    private String message;

    /**
     * Executes the command by saving the task list to storage.
     * <p>
     * On success, the message contains the save confirmation.
     * On failure, the message contains an error message.
     * </p>
     *
     * @param storage the storage handler used to save tasks
     * @param tasks the current task list to be saved
     */
    @Override
    public void execute(Storage storage, TaskList tasks) {
        try {
            this.message = storage.save(tasks);
        } catch (IOException e) {
            this.message = String.format("Error: File writing was unsuccessful. %s", e.getMessage());
        }
    }

    /**
     * Returns the message set during execution.
     *
     * @return the confirmation or error message
     */
    @Override
    public String toString() {
        return this.message;
    }
}
