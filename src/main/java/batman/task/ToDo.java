package batman.task;

public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    public ToDo(boolean done, String description) {
        super(done, description);
    }

    @Override
    public String getTaskType() {
        return "T";
    }

    @Override
    public String toCsv() {
        return String.format("%s, %s", this.getTaskType(),  super.toCsv());
    }

    @Override
    public String toString() {
        return String.format("[%s]%s", this.getTaskType(), super.toString());
    }
}