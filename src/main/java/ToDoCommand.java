public class ToDoCommand extends Command {
    private final String description;

    public ToDoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(Storage storage, TaskList tasks) {
        tasks.addTask(new ToDo(this.description));
    }
}