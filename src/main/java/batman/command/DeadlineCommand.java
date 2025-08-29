package batman.command;

import batman.storage.Storage;
import batman.task.Deadline;
import batman.task.TaskList;

/**
 * Represents the command to add a {@link Deadline} task.
 * <p>
 * When executed, this command creates a new deadline task with the
 * specified description and due date, and adds it to the task list.
 * </p>
 */
public class DeadlineCommand extends AddTaskCommand {
    /** Description of the deadline task. */
    private final String description;

    /** Due date of the deadline task in ISO-8601 format (yyyy-MM-dd). */
    private final String deadline;

    /** The task list reference updated during execution. */
    private TaskList tasks;

    /**
     * Creates a {@code DeadlineCommand} with the given description and deadline.
     *
     * @param description description of the deadline task
     * @param deadline due date in ISO-8601 format (yyyy-MM-dd)
     */
    public DeadlineCommand(String description, String deadline) {
        this.description = description;
        this.deadline = deadline;
    }

    /**
     * Executes the command by adding a new deadline task to the list.
     *
     * @param storage the storage handler (not used in this command)
     * @param tasks the task list to add the deadline task to
     */
    @Override
    public void execute(Storage storage, TaskList tasks) {
        this.tasks = tasks;
        this.tasks.addTask(new Deadline(this.description, this.deadline));
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
