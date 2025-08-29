package batman.task;

/**
 * Represents a generic task with a description and completion status.
 * <p>
 * This is an abstract class that serves as the base for specific types
 * of tasks such as ToDo, Deadline, and Event. Each subclass must define
 * its own task type by implementing {@link #getTaskType()}.
 * </p>
 */
public abstract class Task {
    /** Description of the task. */
    private final String description;

    /** Completion status of the task (true if done, false otherwise). */
    private boolean isDone;

    /**
     * Creates a new task with the given description.
     * The task will be marked as not done by default.
     *
     * @param description description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Creates a new task with the given completion status and description.
     *
     * @param done whether the task is already completed
     * @param description description of the task
     */
    public Task(boolean done, String description) {
        this.isDone = done;
        this.description = description;
    }

    /**
     * Returns the type of this task.
     * <p>
     * Each subclass must provide its own type label
     * (e.g., "T" for ToDo, "D" for Deadline).
     * </p>
     *
     * @return a string representing the task type
     */
    public abstract String getTaskType();

    /**
     * Marks this task as done.
     */
    public void setMarked() {
        this.isDone = true;
    }

    /**
     * Marks this task as not done.
     */
    public void setUnmarked() {
        this.isDone = false;
    }

    /**
     * Returns a CSV-formatted string representation of this task.
     * <p>
     * The format is: {@code done, description}.
     * Example: {@code true, read book}
     * </p>
     *
     * @return CSV string representing the task
     */
    public String toCsv() {
        return String.format("%b, %s", this.isDone, this.description);
    }

    /**
     * Returns a string representation of this task for display purposes.
     * <p>
     * The format is: {@code [X] description} if done,
     * or {@code [ ] description} if not done.
     * </p>
     *
     * @return string representation of the task
     */
    @Override
    public String toString() {
        String completed = this.isDone ? "[X]" : "[ ]";
        return String.format("%s %s", completed, this.description);
    }
}
