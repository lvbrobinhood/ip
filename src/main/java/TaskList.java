import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TaskList {
    private ArrayList<Task> tasks;
    private static final HashMap<String, CommandType> MAPPING = new HashMap<>(Map.of(
            "T", CommandType.TODO,
            "D", CommandType.DEADLINE,
            "E", CommandType.EVENT
    ));

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public void addTask(String descr) throws NoDescriptionException, InvalidCommandException,
            NoDeadlineException, NoFromToException {
        CommandType type = CommandType.fromString(descr);
        if (type == null) {
            throw new InvalidCommandException();
        }

        switch (type) {
        case TODO:
            if (descr.length() <= 4 || descr.substring(4).isBlank()) {
                throw new NoDescriptionException();
            }
            this.tasks.add(new ToDo(descr.substring(4).strip()));
            break;

        case DEADLINE:
            String[] temp = new String[1];
            if (descr.length() > 8) {
                temp = descr.substring(8).split("/by");
            }
            if (!descr.contains("/by") || temp.length != 2 || temp[1].isBlank()) {
                throw new NoDeadlineException();
            } else if (temp[0].isBlank()) {
                throw new NoDescriptionException();
            }
            this.tasks.add(new Deadline(descr.substring(8)));
            break;

        case EVENT:
            if (!descr.contains("/from") || !descr.contains("/to")) {
                throw new NoFromToException();
            } else {
                String[] temp1 = descr.substring(5).split("/from");
                String[] temp2 = descr.substring(5).split("/to");
                if (temp1[0].isBlank()) {
                    throw new NoDescriptionException();
                } else if (temp2.length != 2 || temp2[1].isBlank()) {
                    throw new NoFromToException();
                } else if (temp1[1].split("/to").length != 2 || temp1[1].split("/to")[0].isBlank()) {
                    throw new NoFromToException();
                }
            }
            this.tasks.add(new Event(descr.substring(5)));
            break;
        }

        System.out.println(Batman.line + "Got it. I've added this task:\n" + this.tasks.get(this.tasks.size() - 1)
                + String.format("\nNow you have %d tasks in the list\n", this.tasks.size()) + Batman.line);

    }

    public Task deleteTask(int index) {
        Task removed = this.tasks.remove(index);
        System.out.println(Batman.line + "Noted. I've removed this task:");
        System.out.println(removed);
        System.out.println(String.format("Now you have %d tasks in the list.\n", this.tasks.size()) + Batman.line);
        return removed;
    }

    public void printTaskList() {
        System.out.println(Batman.line + "Here are the tasks in your list:\n" + this + Batman.line);
    }

    public void saveTaskList(String fileName) throws IOException {
        String FOLDER = "./data";
        String FILE_NAME = "tasks.csv";

        if (FOLDER + "/" + FILE_NAME == fileName) {
            File folder = new File(FOLDER);
            if (!folder.exists()) {
                folder.mkdirs();
            }

            FileWriter writer = new FileWriter(new File(folder, FILE_NAME));
            for (int i = 0; i < this.tasks.size(); i++) {
                writer.write(this.tasks.get(i).toCsv() + "\n");
            }
            writer.close();
            System.out.println("File written successfully at " + folder.getAbsolutePath());
        }
    }

    public void loadTaskList(String fileName) {
        File f = new File(fileName);

        if (!f.exists()) {
            System.out.println("File not found");
            return;
        }

        System.out.println("Loading previous task list history...");

        try {
            Scanner sc = new Scanner(f);
            while (sc.hasNext()) {
                String[] currRow = sc.nextLine().split(",");
                currRow = Arrays.stream(currRow).map(String::strip).toArray(String[]::new);
                CommandType currType = MAPPING.get(currRow[0]);

                switch (currType) {
                case TODO:
                    this.tasks.add(new ToDo(currRow[1].equalsIgnoreCase("true"), currRow[2]));
                    break;
                case DEADLINE:
                    this.tasks.add(new Deadline(currRow[1].equalsIgnoreCase("true"),
                            currRow[2], currRow[3]));
                    break;
                case EVENT:
                    this.tasks.add(new Event(currRow[1].equalsIgnoreCase("true"),
                            currRow[2], currRow[3], currRow[4]));
                    break;
                }
            }

            System.out.println("Task list history successfully loaded.");

        } catch (FileNotFoundException e) {
            System.out.println("No history found. Start chatting now:");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Error: ./data/task.csv file corrupted. The file will be overwritten.");
        }
    }

    public void changeDateFormat(String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern.strip());
        for (Task task: this.tasks) {
            if (task instanceof TimedTask) {
                TimedTask timedTask = (TimedTask) task;
                timedTask.setFormatter(formatter);
            }
        }
        System.out.println(Batman.line + "Date format changed successfully.\n" + Batman.line);
    }

    @Override
    public String toString() {
        String output = "";
        for (int i = 0; i < this.tasks.size(); i++) {
            String entry = String.format("%d. %s\n", i + 1, this.tasks.get(i).toString());
            output += entry;
        }
        return output;
    }
}