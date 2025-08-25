import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends TimedTask {
    private final LocalDate from;
    private final LocalDate to;
    private DateTimeFormatter formatter;

    public Event(String description) {
        super(description.split("/from")[0].strip());
        this.to = LocalDate.parse(description.split("/to")[1].strip());
        String temp = description.split("/from")[1].strip();
        this.from = LocalDate.parse(temp.split("/to")[0].strip());
    }

    public Event(boolean done, String description, String from, String to) {
        super(done, description);
        this.from = LocalDate.parse(from);
        this.to = LocalDate.parse(to);
    }

    @Override
    public void setFormatter(DateTimeFormatter formatter) {
        this.formatter = formatter;
    }

    @Override
    public String getTaskType() {
        return "E";
    }

    @Override
    public String toCsv() {
        return String.format("%s, %s, %s, %s", this.getTaskType(), super.toCsv(), this.from, this.to);
    }

    @Override
    public String toString() {
        if (this.formatter != null) {
            return String.format("[%s]%s (from: %s to: %s)", this.getTaskType(), super.toString(),
                    this.from.format(this.formatter), this.to.format(this.formatter));
        }
        return String.format("[%s]%s (from: %s to: %s)", this.getTaskType(), super.toString(), this.from, this.to);
    }
}