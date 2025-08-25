import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends TimedTask {
    private final LocalDate deadline;
    private DateTimeFormatter formatter;

    public Deadline(String description) {
        super(description.split("/by")[0].strip());
        String[] split = description.split("/by");
        this.deadline = LocalDate.parse(split[1].strip());
    }

    public Deadline(boolean done, String description, String deadline) {
        super(done, description);
        this.deadline = LocalDate.parse(deadline);
    }

    @Override
    public void setFormatter(DateTimeFormatter formatter) {
        this.formatter = formatter;
    }

    @Override
    public String getTaskType() {
        return "D";
    }

    @Override
    public String toCsv() {
        return String.format("%s, %s, %s", this.getTaskType(),  super.toCsv(), this.deadline);
    }

    @Override
    public String toString() {
        if (this.formatter != null) {
            return String.format("[%s]%s (by: %s)", this.getTaskType(), super.toString(),
                    this.deadline.format(this.formatter));
        }
        return String.format("[%s]%s (by: %s)", this.getTaskType(), super.toString(), this.deadline);
    }
}