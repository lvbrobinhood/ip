package batman.task;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public int getSize() {
        return tasks.size();
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public Task deleteTask(int index) {
        Task removed = this.tasks.remove(index);
        return removed;
    }

    public void changeDateFormat(String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern.strip());
        for (Task task: this.tasks) {
            if (task instanceof TimedTask) {
                TimedTask timedTask = (TimedTask) task;
                timedTask.setFormatter(formatter);
            }
        }
    }

    @Override
    public String toString() {
        String output = "Here are the tasks in your list:\n";
        for (int i = 0; i < this.tasks.size(); i++) {
            String entry = String.format("%d. %s\n", i + 1, this.tasks.get(i).toString());
            output += entry;
        }
        return output.substring(0, output.length() - 1);
    }
}