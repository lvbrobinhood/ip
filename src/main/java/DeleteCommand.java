public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(Storage storage, TaskList tasks) {
        if (this.index < tasks.getSize()) {
            tasks.deleteTask(index);
        } else {
            System.out.println("Error: Index to delete exceeds length of list");
        }
    }
}