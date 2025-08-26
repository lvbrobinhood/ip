public class MarkCommand extends Command {
    private final int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(Storage storage, TaskList tasks) {
        if (this.index < tasks.getSize()) {
            tasks.getTask(index).setMarked();
        } else {
            System.out.println("Error: Index to be marked exceeds length of list");
        }
    }
}