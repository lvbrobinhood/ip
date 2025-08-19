import java.util.Scanner;
import java.util.ArrayList;

public class Batman {
    private static ArrayList<Task> taskList = new ArrayList<>(100);
    private static final String line = "_____________________________________________________\n";

    private static void addToList(String descr) throws NoDescriptionException, InvalidCommandException,
            NoDeadlineException, NoFromToException {
        if (descr.startsWith("todo")) {
            if (descr.length() <= 4 || descr.substring(4).isBlank()) {
                throw new NoDescriptionException();
            }
            taskList.add(new ToDo(descr.substring(4).strip()));

        } else if (descr.startsWith("deadline")) {
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

        } else if (descr.startsWith("event")) {
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

        } else {
            throw new InvalidCommandException();
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

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println( Batman.line + "Hello! I'm Batman.\n" + "What can I do for you?\n" + Batman.line);

        while (true) {
            String input = sc.nextLine();

            // Exit case
            if (input.equals("bye")) {
                System.out.println(Batman.line + "Bye. Hope to see you again soon!\n" + Batman.line);
                break;

                // List case
            } else if (input.equals("list")) {
                Batman.printList();

                // Mark task list items
            } else if (input.startsWith("mark") && input.length() >= 6) {
                String substr = input.substring(5);
                try {
                    int index = Integer.parseInt(substr) - 1;
                    if (index < taskList.size()) {
                        taskList.get(index).mark();
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Error: Argument must be an integer");
                }

                // Unmark task list items
            } else if (input.startsWith("unmark") && input.length() >= 8) {
                String substr = input.substring(7);
                try {
                    int index = Integer.parseInt(substr) - 1;
                    if (index < taskList.size()) {
                        taskList.get(index).unmark();
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Error: Argument must be an integer");
                }

                //delete task list items
            } else if (input.startsWith("delete") && input.length() >= 8) {
                String substr = input.substring(7);
                try {
                    int index = Integer.parseInt(substr) - 1;
                    if (index < taskList.size()) {
                        deleteFromList(index);
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Error: Argument must be an integer");
                }
            } else {
                try {
                    Batman.addToList(input);
                } catch (NoDescriptionException | InvalidCommandException | NoDeadlineException | NoFromToException e) {
                    System.out.println(e.getMessage());
                }
            }
        }

        sc.close();
    }
}
