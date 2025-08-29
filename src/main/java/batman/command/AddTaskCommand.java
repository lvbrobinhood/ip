package batman.command;

import batman.task.TaskList;

/**
 * Represents an abstract command that adds a task to the task list.
 * <p>
 * Subclasses should handle the actual creation and addition of the specific
 * task type. This class provides a helper method for generating a confirmation
 * message after a task has been added.
 * </p>
 */
public abstract class AddTaskCommand extends Command {
    /**
     * Returns a confirmation message after a task has been added to the list.
     * <p>
     * The message includes the task just added and the updated number of tasks
     * in the list.
     * </p>
     *
     * @param tasks the task list containing the newly added task
     * @return confirmation message with task details and updated count
     */
    public String getAddedTask(TaskList tasks) {
        return "Got it. I've added this task:\n" + tasks.getTask(tasks.getSize() - 1)
                + String.format("\nNow you have %d tasks in the list", tasks.getSize());
    }
}
