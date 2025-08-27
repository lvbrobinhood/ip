package batman.task;

import java.time.format.DateTimeFormatter;

public abstract class TimedTask extends Task {
    public TimedTask(String description) {
        super(description);
    }

    public TimedTask(boolean done, String description) {
        super(done, description);
    }

    public abstract void setFormatter(DateTimeFormatter formatter);
}