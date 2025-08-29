package batman.task;

import java.time.format.DateTimeFormatter;

/**
 * Represents a task that is associated with a specific time or date.
 * <p>
 * This is an abstract class extending {@link Task}, intended for tasks
 * such as deadlines or events that require a date/time component.
 * </p>
 */
public abstract class TimedTask extends Task {
    /**
     * Creates a new timed task with the given description.
     * The task will be marked as not done by default.
     *
     * @param description description of the task
     */
    public TimedTask(String description) {
        super(description);
    }

    /**
     * Creates a new timed task with the given completion status and description.
     *
     * @param done whether the task is already completed
     * @param description description of the task
     */
    public TimedTask(boolean done, String description) {
        super(done, description);
    }

    /**
     * Sets the formatter used to display the date and/or time of this task.
     *
     * @param formatter the {@link DateTimeFormatter} to use for formatting
     */
    public abstract void setFormatter(DateTimeFormatter formatter);
}
