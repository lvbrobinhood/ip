import java.util.Scanner;

public class Batman {
    private static String[] list = new String[100];
    private static int pointer = 0;
    private static final String line = "_____________________________________________________\n";

    private static void addToList(String item) {
        if (pointer != 100) {
            list[pointer] = item;
            pointer++;

            String entry = String.format("added: %s\n", item);
            System.out.println(Batman.line + entry + Batman.line);
        }
    }

    private static void printList() {
        String output = Batman.line;
        for (int i = 0; i < pointer; i++) {
            String entry = String.format("%d. %s\n", i + 1, list[i]);
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
            } else {
                Batman.addToList(input);
            }
        }

        sc.close();
    }
}
