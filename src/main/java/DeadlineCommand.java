public class DeadlineCommand extends Command {
    private final String description;
    private final String deadline;

    public DeadlineCommand(String description, String deadline) {
        this.description = description;
        this.deadline = deadline;
    }

    @Override
    public void execute(Storage storage, TaskList tasks) {
        tasks.addTask(new Deadline(this.description, this.deadline));
    }
}