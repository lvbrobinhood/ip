public abstract class Task {
    private final String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(boolean done, String description) {
        this.isDone = done;
        this.description = description;
    }

    public abstract String getTaskType();

    public void setMarked() {
        this.isDone = true;
    }

    public void setUnmarked() {
        this.isDone = false;
    }

    public String toCsv() {
        return String.format("%b, %s", this.isDone, this.description);
    }

    @Override
    public String toString() {
        String completed = this.isDone ? "[X]" : "[ ]";
        return String.format("%s %s", completed, this.description);
    }
}