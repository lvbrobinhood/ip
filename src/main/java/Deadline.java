public class Deadline extends Task {
    private final String deadline;
    public Deadline(String description) {
        super(description.split("/by")[0].strip());
        String[] split = description.split("/by");
        this.deadline = split[1].strip();
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.deadline);
    }
}