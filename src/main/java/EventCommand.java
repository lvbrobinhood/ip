public class EventCommand extends AddTaskCommand {
    private final String description;
    private final String from;
    private final String to;
    private TaskList tasks;

    public EventCommand(String description, String from, String to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    @Override
    public void execute(Storage storage, TaskList tasks) {
        this.tasks = tasks;
        this.tasks.addTask(new Event(this.description, this.from, this.to));
    }

    @Override
    public String toString() {
        return super.getAddedTask(this.tasks);
    }
}