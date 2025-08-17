import java.util.Scanner;

public class Batman {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = "_____________________________________________________\n";
        System.out.println( line + "Hello! I'm Batman.\n" + "What can I do for you?\n" + line);

        while (true) {
            String input = sc.nextLine();

            if (input.equals("bye")) {
                System.out.println(line + "Bye. Hope to see you again soon!\n" + line);
                break;
            } else {
                System.out.println(line + input + "\n" + line);
            }
        }

        sc.close();
    }
}
