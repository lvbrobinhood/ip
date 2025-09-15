package batman.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task with a description and a due date.
 * <p>
 * A deadline is a type of {@link TimedTask} that must be completed
 * by a specific date. An optional formatter can be set to control how
 * the date is displayed in string outputs.
 * </p>
 */
public class Deadline extends TimedTask {
    /** The date by which the task must be completed. */
    private LocalDate deadline;

    /** Formatter for displaying the deadline date, if specified. */
    private DateTimeFormatter formatter;

    /**
     * Creates a new deadline task with the given description and due date.
     * The task will be marked as not done by default.
     *
     * @param description description of the deadline task
     * @param deadline due date of the task in ISO-8601 format (yyyy-MM-dd)
     */
    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = LocalDate.parse(deadline);
    }

    /**
     * Creates a new deadline task with the given completion status, description, and due date.
     *
     * @param done whether the task is already completed
     * @param description description of the deadline task
     * @param deadline due date of the task in ISO-8601 format (yyyy-MM-dd)
     */
    public Deadline(boolean done, String description, String deadline) {
        super(done, description);
        this.deadline = LocalDate.parse(deadline);
    }

    /**
     * Sets the deadline for the task using the provided string.
     * <p>
     * This method parses the given deadline string into a {@code LocalDate} object
     * and assigns it to the {@code deadline} field.
     * </p>
     *
     * @param deadline a string representing the deadline in the format "yyyy-MM-dd"
     */
    public void setDeadline(String deadline) {
        this.deadline = LocalDate.parse(deadline);
    }

    /**
     * Sets the formatter used to display the deadline date.
     *
     * @param formatter the {@link DateTimeFormatter} to format the deadline
     */
    @Override
    public void setFormatter(DateTimeFormatter formatter) {
        this.formatter = formatter;
    }

    /**
     * Returns the type of this task.
     * <p>
     * For deadline tasks, the type is represented by {@code "D"}.
     * </p>
     *
     * @return the string {@code "D"}
     */
    @Override
    public String getTaskType() {
        return "D";
    }

    /**
     * Returns a CSV-formatted string representation of this deadline task.
     * <p>
     * The format is: {@code D, done, description, deadline}.
     * Example: {@code D, false, submit report, 2025-09-01}
     * </p>
     *
     * @return CSV string representing the deadline task
     */
    @Override
    public String toCsv() {
        return String.format("%s, %s, %s", this.getTaskType(), super.toCsv(), this.deadline);
    }

    /**
     * Returns a string representation of this deadline task for display purposes.
     * <p>
     * The format is: {@code [D][X] description (by: formattedDate)} if done,
     * or {@code [D][ ] description (by: formattedDate)} if not done.
     * </p>
     * If a formatter has been set, the deadline is formatted using it;
     * otherwise, the raw {@link LocalDate} is displayed.
     *
     * @return string representation of the deadline task
     */
    @Override
    public String toString() {
        if (this.formatter != null) {
            return String.format("[%s]%s (by: %s)", this.getTaskType(), super.toString(),
                    this.deadline.format(this.formatter));
        }
        return String.format("[%s]%s (by: %s)", this.getTaskType(), super.toString(), this.deadline);
    }
}
