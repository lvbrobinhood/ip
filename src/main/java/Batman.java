import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

import java.time.format.DateTimeParseException;
import java.time.format.DateTimeFormatter;

public class Batman {
    private static ArrayList<Task> taskList = new ArrayList<>(100);
    private static final String FILE = "./data/tasks.csv";
    private static final HashMap<String, CommandType> MAPPING = new HashMap<>(Map.of(
            "T", CommandType.TODO,
            "D", CommandType.DEADLINE,
            "E", CommandType.EVENT
    ));
    private static final String line = "_____________________________________________________\n";

    // Creates a new task and adds it to taskList if it is of correct task type
    private static void addToList(String descr) throws NoDescriptionException, InvalidCommandException,
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
            taskList.add(new ToDo(descr.substring(4).strip()));
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
            taskList.add(new Deadline(descr.substring(8)));
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
            taskList.add(new Event(descr.substring(5)));
            break;
        }

        System.out.println(Batman.line + "Got it. I've added this task:\n" + taskList.get(taskList.size() - 1)
                + String.format("\nNow you have %d tasks in the list\n", taskList.size()) + Batman.line);

    }

    private static void deleteFromList(int index) {
        Task removed = taskList.remove(index);
        System.out.println(Batman.line + "Noted. I've removed this task:");
        System.out.println(removed);
        System.out.println(String.format("Now you have %d tasks in the list.\n", taskList.size()) + Batman.line);
    }

    private static void printList() {
        String output = Batman.line + "Here are the tasks in your list:\n";
        for (int i = 0; i < taskList.size(); i++) {
            String entry = String.format("%d. %s\n", i + 1, taskList.get(i).toString());
            output += entry;
        }
        output += Batman.line;
        System.out.println(output);
    }

    private static void saveTaskList() throws IOException {
        File folder = new File("./data");
        if (!folder.exists()) {
            folder.mkdirs();
        }

        FileWriter writer = new FileWriter(new File(folder, "tasks.csv"));
        for (int i = 0; i < taskList.size(); i++) {
            writer.write(taskList.get(i).toCsv() + "\n");

        }
        writer.close();
        System.out.println("File written successfully at " + folder.getAbsolutePath());
    }

    private static void loadTaskList() {
        File f = new File(FILE);

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
                    taskList.add(new ToDo(currRow[1].equalsIgnoreCase("true"), currRow[2]));
                    break;
                case DEADLINE:
                    taskList.add(new Deadline(currRow[1].equalsIgnoreCase("true"),
                            currRow[2], currRow[3]));
                    break;
                case EVENT:
                    taskList.add(new Event(currRow[1].equalsIgnoreCase("true"),
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

    private static void changeDateFormat(String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern.strip());
        for (Task task: taskList) {
            if (task instanceof TimedTask) {
                TimedTask timedTask = (TimedTask) task;
                timedTask.setFormatter(formatter);
            }
        }
        System.out.println(line + "Date format changed successfully.\n" + line);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        loadTaskList();
        System.out.println(Batman.line + "Hello! I'm Batman.\n" + "What can I do for you?\n" + Batman.line);

        while (true) {
            String input = sc.nextLine();

            if (input.equals("bye")) {
                try {
                    saveTaskList();
                } catch (IOException e) {
                    System.out.println(String.format("Error: File writing was unsuccessful. %s",
                            e.getMessage()));
                }

                System.out.println(Batman.line + "Bye. Hope to see you again soon!\n" + Batman.line);
                break;

            } else if (input.equals("list")) {
                Batman.printList();

            } else if (input.startsWith("mark") && input.length() >= 6) {
                String substr = input.substring(5);
                try {
                    int index = Integer.parseInt(substr) - 1;
                    taskList.get(index).setMarked();
                } catch (NumberFormatException e) {
                    System.out.println("Error: Argument must be an integer");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Error: Index to be marked exceeds length of list");
                }

            } else if (input.startsWith("unmark") && input.length() >= 8) {
                String substr = input.substring(7);
                try {
                    int index = Integer.parseInt(substr) - 1;
                    taskList.get(index).setUnmarked();
                } catch (NumberFormatException e) {
                    System.out.println("Error: Argument must be an integer");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Error: Index to be unmarked exceeds length of list");
                }

            } else if (input.startsWith("delete") && input.length() >= 8) {
                String substr = input.substring(7);
                try {
                    int index = Integer.parseInt(substr) - 1;
                    deleteFromList(index);
                } catch (NumberFormatException e) {
                    System.out.println("Error: Argument must be an integer");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Error: Index to delete from exceeds length of list");
                }

            } else if (input.startsWith("formatdate ") && input.length() >= 12) {
                String substr = input.substring(11);
                changeDateFormat(substr.strip());
            } else {
                try {
                    Batman.addToList(input);
                } catch (NoDescriptionException | InvalidCommandException | NoDeadlineException | NoFromToException e) {
                    System.out.println(e.getMessage());
                } catch (DateTimeParseException e) {
                    System.out.println("Error: Please use yyyy-mm-dd format for time");
                }
            }
        }

        sc.close();
    }
}
