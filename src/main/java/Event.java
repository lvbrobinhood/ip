public class Event extends Task {
    private final String from;
    private final String to;
    public Event(String description) {
        super(description.split("/from")[0].strip());
        this.to = description.split("/to")[1].strip();
        String temp = description.split("/from")[1].strip();
        this.from = temp.split("/to")[0].strip();
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), this.from, this.to);
    }
}