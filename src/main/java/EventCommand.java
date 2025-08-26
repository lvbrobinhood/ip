public class EventCommand extends Command {
    private final String description;
    private final String from;
    private final String to;

    public EventCommand(String description, String from, String to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    @Override
    public void execute(Storage storage, TaskList tasks) {
        tasks.addTask(new Event(this.description, this.from, this.to));
    }
}