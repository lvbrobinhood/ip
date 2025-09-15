package batman.command;

import batman.storage.Storage;
import batman.task.TaskList;

/**
 * Command to find tasks that match a given keyword.
 * <p>
 * This command filters the tasks in the task list based on a keyword and returns
 * the tasks that contain the keyword in their description.
 * </p>
 */
public class FindCommand extends Command {
    private final String keyword;
    private TaskList tasks;

    /**
     * Creates a new {@code FindCommand} with the specified keyword.
     * <p>
     * The keyword is used to filter tasks that contain the keyword in their description.
     * </p>
     *
     * @param keyword the keyword used to search for tasks
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the command by filtering the tasks based on the provided keyword.
     * <p>
     * This method updates the {@code tasks} field with a filtered list of tasks that
     * contain the keyword in their description.
     * </p>
     *
     * @param storage the storage object to access the task list (not used in this method)
     * @param tasks the list of tasks to search through
     */
    @Override
    public void execute(Storage storage, TaskList tasks) {
        this.tasks = tasks.filterTasks(keyword);
    }

    /**
     * Returns a string representation of the result of the find operation.
     * <p>
     * If no tasks are found that match the keyword, it returns a message indicating
     * no tasks were found. If matching tasks are found, it returns a list of those tasks.
     * </p>
     *
     * @return a string indicating the results of the find operation
     */
    @Override
    public String toString() {
        if (this.tasks.getSize() == 0) {
            return "No tasks found";
        }
        return "Here are the matching tasks in your list:\n" + this.tasks.toString();
    }
}
