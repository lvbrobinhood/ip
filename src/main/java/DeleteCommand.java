public class DeleteCommand extends Command {
    private boolean isSuccess;
    private final int index;
    private Task removed;
    private int size;

    public DeleteCommand(int index) {
        this.index = index;
        this.isSuccess = false;
    }

    @Override
    public void execute(Storage storage, TaskList tasks) {
        if (this.index < tasks.getSize()) {
            this.isSuccess = true;
            this.removed = tasks.deleteTask(index);
            this.size = tasks.getSize();
        }
    }

    @Override
    public String toString() {
        if (!this.isSuccess) {
            return "Error: Index to delete exceeds length of list";
        }
        return "Noted. I've removed this task:\n" + this.removed
                + String.format("\nNow you have %d tasks in the list.", this.size);
    }
}