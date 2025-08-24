public abstract class Task {
    private final String description;
    private boolean isDone;
    private static final String line = "_____________________________________________________";

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
        System.out.println(Task.line);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(this);
        System.out.println(Task.line);
    }

    public void setUnmarked() {
        this.isDone = false;
        System.out.println(Task.line);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(this);
        System.out.println(Task.line);
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