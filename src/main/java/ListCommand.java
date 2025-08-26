public class ListCommand extends Command {
    @Override
    public void execute(Storage storage, TaskList tasks) {
        tasks.printTaskList();
    }
}