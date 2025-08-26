public class UnmarkCommand extends Command {
    private final int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(Storage storage, TaskList tasks) {
        if (this.index < tasks.getSize()) {
            tasks.getTask(index).setUnmarked();
        } else {
            System.out.println("Error: Index to be unmarked exceeds length of list");
        }
    }
}