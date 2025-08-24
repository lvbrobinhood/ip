public class Deadline extends Task {
    private final String deadline;

    public Deadline(String description) {
        super(description.split("/by")[0].strip());
        String[] split = description.split("/by");
        this.deadline = split[1].strip();
    }

    public Deadline(boolean done, String description, String deadline) {
        super(done, description);
        this.deadline = deadline;
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
        return String.format("[%s]%s (by: %s)", this.getTaskType(), super.toString(), this.deadline);
    }
}