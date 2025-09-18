package batman.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task with a description and a start and end date.
 * <p>
 * An event is a type of {@link TimedTask} that spans a time period
 * defined by a "from" date and a "to" date. An optional formatter can be
 * set to control how dates are displayed in string outputs.
 * </p>
 */
public class Event extends TimedTask {
    /** The start date of the event. */
    private final LocalDate from;

    /** The end date of the event. */
    private final LocalDate to;

    /** Formatter for displaying event dates, if specified. */
    private DateTimeFormatter formatter;

    /**
     * Creates a new event task with the given description, start date, and end date.
     * The task will be marked as not done by default.
     *
     * @param description description of the event
     * @param from start date in ISO-8601 format (yyyy-MM-dd)
     * @param to end date in ISO-8601 format (yyyy-MM-dd)
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = LocalDate.parse(from);
        this.to = LocalDate.parse(to);
    }

    /**
     * Creates a new event task with the given completion status, description, start date, and end date.
     *
     * @param done whether the task is already completed
     * @param description description of the event
     * @param from start date in ISO-8601 format (yyyy-MM-dd)
     * @param to end date in ISO-8601 format (yyyy-MM-dd)
     */
    public Event(boolean done, String description, String from, String to) {
        super(done, description);
        this.from = LocalDate.parse(from);
        this.to = LocalDate.parse(to);
    }

    /**
     * Sets the formatter used to display the start and end dates of this event.
     *
     * @param formatter the {@link DateTimeFormatter} to format the dates
     */
    @Override
    public void setFormatter(DateTimeFormatter formatter) {
        this.formatter = formatter;
    }

    /**
     * Returns the type of this task.
     * <p>
     * For event tasks, the type is represented by {@code "E"}.
     * </p>
     *
     * @return the string {@code "E"}
     */
    @Override
    public String getTaskType() {
        return "E";
    }

    /**
     * Returns a CSV-formatted string representation of this event task.
     * <p>
     * The format is: {@code E, done, description, from, to}.
     * Example: {@code E, false, project meeting, 2025-09-01, 2025-09-02}
     * </p>
     *
     * @return CSV string representing the event task
     */
    @Override
    public String toCsv() {
        return String.format("%s, %s, %s, %s", this.getTaskType(), super.toCsv(), this.from, this.to);
    }

    /**
     * Returns a string representation of this event task for display purposes.
     * <p>
     * The format is: {@code [E][X] description (from: startDate to: endDate)} if done,
     * or {@code [E][ ] description (from: startDate to: endDate)} if not done.
     * </p>
     * If a formatter has been set, the dates are formatted using it;
     * otherwise, the raw {@link LocalDate} values are displayed.
     *
     * @return string representation of the event task
     */
    @Override
    public String toString() {
        if (this.formatter != null) {
            return String.format("[%s]%s (from: %s to: %s)", this.getTaskType(), super.toString(),
                    this.from.format(this.formatter), this.to.format(this.formatter));
        }
        DateTimeFormatter baseFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return String.format("[%s]%s (from: %s to: %s)", this.getTaskType(), super.toString(),
                this.from.format(baseFormatter), this.to.format(baseFormatter));
    }
}
