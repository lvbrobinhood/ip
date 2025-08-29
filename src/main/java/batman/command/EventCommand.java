package batman.command;

import batman.storage.Storage;
import batman.task.Event;
import batman.task.TaskList;

/**
 * Represents the command to add an {@link Event} task.
 * <p>
 * When executed, this command creates a new event task with the
 * specified description, start date, and end date, and adds it
 * to the task list.
 * </p>
 */
public class EventCommand extends AddTaskCommand {
    /** Description of the event task. */
    private final String description;

    /** Start date of the event in ISO-8601 format (yyyy-MM-dd). */
    private final String from;

    /** End date of the event in ISO-8601 format (yyyy-MM-dd). */
    private final String to;

    /** The task list reference updated during execution. */
    private TaskList tasks;

    /**
     * Creates an {@code EventCommand} with the given description, start date, and end date.
     *
     * @param description description of the event task
     * @param from start date in ISO-8601 format (yyyy-MM-dd)
     * @param to end date in ISO-8601 format (yyyy-MM-dd)
     */
    public EventCommand(String description, String from, String to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    /**
     * Executes the command by adding a new event task to the list.
     *
     * @param storage the storage handler (not used in this command)
     * @param tasks the task list to add the event task to
     */
    @Override
    public void execute(Storage storage, TaskList tasks) {
        this.tasks = tasks;
        this.tasks.addTask(new Event(this.description, this.from, this.to));
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
