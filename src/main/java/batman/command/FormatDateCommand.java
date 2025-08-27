package batman.command;

import batman.storage.Storage;
import batman.task.TaskList;

public class FormatDateCommand extends Command {
    private boolean isSuccess;
    private final String format;

    public FormatDateCommand(String format) {
        this.format = format.strip();
        this.isSuccess = false;
    }

    @Override
    public void execute(Storage storage, TaskList tasks) {
        try {
            tasks.changeDateFormat(format);
            this.isSuccess = true;
        } catch (IllegalArgumentException e) {
            this.isSuccess = false;
        }
    }

    @Override
    public String toString() {
        if (isSuccess) {
            return "Date format changed successfully.";
        } else {
            return "Error: Please use a valid date format (Use MM for month, not mm)";
        }
    }
}