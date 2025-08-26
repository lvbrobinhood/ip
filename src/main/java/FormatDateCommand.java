public class FormatDateCommand extends Command {
    private final String format;

    public FormatDateCommand(String format) {
        this.format = format.strip();
    }

    @Override
    public void execute(Storage storage, TaskList tasks) {
        try {
            tasks.changeDateFormat(format);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: Please use a valid date format (Use MM for month, not mm)");
        }
    }
}