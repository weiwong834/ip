import java.util.ArrayList;
import java.util.Scanner;

public class Squid {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> tasks = new ArrayList<>();

        String line = "____________________________________________________________\n";
        String greeting = " Hello! I'm Squid\n"
                        + " What can I do for you?\n";
        String goodbye = " Bye. Hope to see you again soon!\n";
        System.out.println(line + greeting + line);
        while (true) {
            if (!sc.hasNextLine()) break;
            String input = sc.nextLine();

            if (input.equals("bye")) {
                System.out.println(line + goodbye + line);
                break;
            } else if (input.equals("list")) {
                System.out.println(line);
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println((i + 1) + ". " + tasks.get(i) + "\n");
                }
                System.out.println(line);
            } else {
                tasks.add(input);
                System.out.println(line + "added: " + input + "\n" + line);
            }
        }
        sc.close();
    }
}
