import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Storage {
    private final String directory;
    private final String fileName;
    private static final HashMap<String, CommandType> MAPPING = new HashMap<>(Map.of(
            "T", CommandType.TODO,
            "D", CommandType.DEADLINE,
            "E", CommandType.EVENT
    ));

    public Storage(String directory, String fileName) {
        this.directory = directory;
        this.fileName = fileName;
    }

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