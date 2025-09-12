package batman.task;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Represents a list of tasks.
 * <p>
 * Provides operations for adding, retrieving, deleting, filtering, and formatting tasks.
 * Tasks can be displayed in a user-friendly format or with updated date formatting
 * for all {@link TimedTask} objects.
 * </p>
 */
public class TaskList {
    /** The list of tasks. */
    private final ArrayList<Task> tasks;

    /**
     * Creates an empty task list.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Creates a task list with the given tasks.
     *
     * @param tasks the initial list of tasks
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
        assert this.tasks != null;
    }

    /**
     * Returns the task at the specified index.
     *
     * @param index the index of the task (0-based)
     * @return the task at the given index
     */
    public Task getTask(int index) {
        assert index >= 0 && index < this.tasks.size();
        return tasks.get(index);
    }

    /**
     * Returns the number of tasks in this list.
     *
     * @return the size of the task list
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Adds a task to the list.
     *
     * @param task the task to be added
     */
    public void addTask(Task task) {
        assert task != null;
        this.tasks.add(task);
    }

    /**
     * Deletes the task at the specified index.
     *
     * @param index the index of the task to be removed (0-based)
     * @return the task that was removed
     */
    public Task deleteTask(int index) {
        assert index >= 0 && index < this.tasks.size();
        return this.tasks.remove(index);
    }

    /**
     * Returns a new task list containing only the tasks whose descriptions
     * contain the given keyword.
     *
     * @param keyword the keyword to filter tasks by
     * @return a new {@code TaskList} with tasks matching the keyword
     */
    public TaskList filterTasks(String keyword) {
        assert keyword != null && !keyword.isEmpty();
        return new TaskList(this.tasks.stream()
                .filter(task -> task.hasKeyword(keyword))
                .collect(Collectors.toCollection(ArrayList::new)));
    }

    /**
     * Changes the date formatting of all timed tasks in this list.
     *
     * @param pattern the date format pattern to apply
     */
    public void changeDateFormat(String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern.strip());
        for (Task task: this.tasks) {
            if (task instanceof TimedTask) {
                TimedTask timedTask = (TimedTask) task;
                timedTask.setFormatter(formatter);
            }
        }
    }

    /**
     * Returns a string representation of this task list for display purposes.
     * <p>
     * The format is: each task numbered on a new line.
     * Example:
     * <pre>
     * Here are the tasks in your list:
     * 1. [T][ ] read book
     * 2. [D][X] submit report (by: 2025-09-01)
     * </pre>
     * </p>
     *
     * @return string representation of the task list
     */
    @Override
    public String toString() {
        String output = "";
        for (int i = 0; i < this.tasks.size(); i++) {
            String entry = String.format("%d. %s\n", i + 1, this.tasks.get(i).toString());
            output += entry;
        }
        return output.substring(0, output.length() - 1);
    }
}
