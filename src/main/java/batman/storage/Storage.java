package batman.storage;

import batman.task.Deadline;
import batman.task.Event;
import batman.task.TaskList;
import batman.task.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Handles saving and loading tasks to and from a file.
 * <p>
 * Tasks are stored in CSV format, where each line represents a task.
 * This class supports {@link ToDo}, {@link Deadline}, and {@link Event} tasks.
 * </p>
 */
public class Storage {
    /** Directory path where the task file is stored. */
    private final String directory;

    /** File name of the task storage file. */
    private final String fileName;

    /** Mapping of task type identifiers to {@link CommandType}. */
    private static final HashMap<String, CommandType> MAPPING = new HashMap<>(Map.of(
            "T", CommandType.TODO,
            "D", CommandType.DEADLINE,
            "E", CommandType.EVENT
    ));

    /**
     * Creates a new {@code Storage} object with the given directory and file name.
     *
     * @param directory the directory path where the task file is stored
     * @param fileName the file name of the task storage file
     */
    public Storage(String directory, String fileName) {
        this.directory = directory;
        this.fileName = fileName;
    }

    /**
     * Loads tasks from the storage file into the given {@link TaskList}.
     * <p>
     * If the file does not exist, no tasks are loaded. If the file is
     * corrupted, an error message is displayed and the file will be overwritten
     * on the next save.
     * </p>
     *
     * @param tasks the task list to populate with loaded tasks
     */
    public void load(TaskList tasks) {
        File f = new File(this.directory + "/" + this.fileName);

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
                    tasks.addTask(new ToDo(currRow[1].equalsIgnoreCase("true"), currRow[2]));
                    break;
                case DEADLINE:
                    tasks.addTask(new Deadline(currRow[1].equalsIgnoreCase("true"),
                            currRow[2], currRow[3]));
                    break;
                case EVENT:
                    tasks.addTask(new Event(currRow[1].equalsIgnoreCase("true"),
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

    /**
     * Saves the given task list to the storage file.
     * <p>
     * If the directory does not exist, it will be created. Each task is
     * written in CSV format using its {@link batman.task.Task#toCsv()} method.
     * </p>
     *
     * @param tasks the task list to save
     * @return a confirmation message with the file path
     * @throws IOException if an error occurs during file writing
     */
    public String save(TaskList tasks) throws IOException {
        File folder = new File(this.directory);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        FileWriter writer = new FileWriter(new File(folder, this.fileName));
        for (int i = 0; i < tasks.getSize(); i++) {
            writer.write(tasks.getTask(i).toCsv() + "\n");
        }
        writer.close();

        return "File written successfully at " + folder.getAbsolutePath();
    }
}
