package batman.task;

/**
 * Represents a to-do task with only a description and completion status.
 * <p>
 * This is the simplest type of task without any time or date component.
 * </p>
 */
public class ToDo extends Task {
    /**
     * Creates a new to-do task with the given description.
     * The task will be marked as not done by default.
     *
     * @param description description of the to-do task
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Creates a new to-do task with the given completion status and description.
     *
     * @param done whether the task is already completed
     * @param description description of the to-do task
     */
    public ToDo(boolean done, String description) {
        super(done, description);
    }

    /**
     * Returns the type of this task.
     * <p>
     * For to-do tasks, the type is represented by {@code "T"}.
     * </p>
     *
     * @return the string {@code "T"}
     */
    @Override
    public String getTaskType() {
        return "T";
    }

    /**
     * Returns a CSV-formatted string representation of this to-do task.
     * <p>
     * The format is: {@code T, done, description}.
     * Example: {@code T, false, read book}
     * </p>
     *
     * @return CSV string representing the to-do task
     */
    @Override
    public String toCsv() {
        return String.format("%s, %s", this.getTaskType(),  super.toCsv());
    }

    /**
     * Returns a string representation of this to-do task for display purposes.
     * <p>
     * The format is: {@code [T][X] description} if done,
     * or {@code [T][ ] description} if not done.
     * </p>
     *
     * @return string representation of the to-do task
     */
    @Override
    public String toString() {
        return String.format("[%s]%s", this.getTaskType(), super.toString());
    }
}
