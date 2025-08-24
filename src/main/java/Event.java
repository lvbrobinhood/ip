public class Event extends Task {
    private final String from;
    private final String to;

    public Event(String description) {
        super(description.split("/from")[0].strip());
        this.to = description.split("/to")[1].strip();
        String temp = description.split("/from")[1].strip();
        this.from = temp.split("/to")[0].strip();
    }

    public Event(boolean done, String description, String from, String to) {
        super(done, description);
        this.from = from;
        this.to = to;
    }

    public String getFrom() {
        return this.from;
    }

    public String getTo() {
        return this.to;
    }

    @Override
    public String getTaskType() {
        return "E";
    }

    @Override
    public String toCsv() {
        return String.format("%s, %s, %s, %s", this.getTaskType(),  super.toCsv(), this.from, this.to);
    }

    @Override
    public String toString() {
        return String.format("[%s]%s (from: %s to: %s)", this.getTaskType(), super.toString(), this.from, this.to);
    }
}