public abstract class AddTaskCommand extends Command {
    public String getAddedTask(TaskList tasks) {
        return "Got it. I've added this task:\n" + tasks.getTask(tasks.getSize() - 1)
                + String.format("\nNow you have %d tasks in the list", tasks.getSize());
    }
}