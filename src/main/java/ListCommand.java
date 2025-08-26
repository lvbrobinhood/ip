public class ListCommand extends Command {
    private TaskList tasks;

    @Override
    public void execute(Storage storage, TaskList tasks) {
        this.tasks = tasks;
    }

    @Override
    public String toString() {
        return tasks.toString();
    }
}