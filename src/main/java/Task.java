public class Task {
    private final String description;
    private boolean done;
    private static final String line = "_____________________________________________________";

    public Task(String description) {
        this.description = description;
        this.done = false;
    }

    public void mark() {
        this.done = true;
        System.out.println(Task.line);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(this);
        System.out.println(Task.line);
    }

    public void unmark() {
        this.done = false;
        System.out.println(Task.line);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(this);
        System.out.println(Task.line);
    }

    public String toString() {
        String completed = done ? "[X]" : "[ ]";
        return String.format("%s %s", completed, description);
    }
}