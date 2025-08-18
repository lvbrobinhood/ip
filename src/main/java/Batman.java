import java.util.Scanner;

public class Batman {
    private static Task[] taskList = new Task[100];
    private static int pointer = 0;
    private static final String line = "_____________________________________________________\n";

    private static void addToList(String description) {
        if (pointer != 100) {
            taskList[pointer] = new Task(description);
            pointer++;

            String entry = String.format("added: %s\n", description);
            System.out.println(Batman.line + entry + Batman.line);
        }
    }

    private static void printList() {
        String output = Batman.line + "Here are the tasks in your list:\n";
        for (int i = 0; i < pointer; i++) {
            String entry = String.format("%d. %s\n", i + 1, taskList[i].toString());
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

            if (input.equals("bye")) {
                System.out.println(Batman.line + "Bye. Hope to see you again soon!\n" + Batman.line);
                break;
            } else if (input.equals("list")) {
                Batman.printList();
            } else if (input.startsWith("mark") && input.length() >= 6) {
                String substr = input.substring(5);
                try {
                    int index = Integer.parseInt(substr) - 1;
                    if (index < Batman.pointer) {
                        taskList[index].mark();
                    }
                } catch (NumberFormatException e) {
                    Batman.addToList(input);
                }

            } else if (input.startsWith("unmark") && input.length() >= 8) {
                String substr = input.substring(7);
                try {
                    int index = Integer.parseInt(substr) - 1;
                    if (index < Batman.pointer) {
                        taskList[index].unmark();
                    }
                } catch (NumberFormatException e) {
                    Batman.addToList(input);
                }

            } else {
                Batman.addToList(input);
            }
        }

        sc.close();
    }
}
