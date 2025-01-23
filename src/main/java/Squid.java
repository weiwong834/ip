import java.util.ArrayList;
import java.util.Scanner;

public class Squid {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();

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
                System.out.println(line + "Here are the tasks in your list:\n");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println((i + 1) + ". " + tasks.get(i) + "\n");
                }
                System.out.println(line);
            } else if (input.startsWith("mark ")) {
                int index = Integer.parseInt(input.substring(5)) - 1;
                Task curr = tasks.get(index);
                curr.setDone();
                System.out.println(line + "Nice! I've marked this task as done:\n");
                System.out.println(curr + line);
            } else if (input.startsWith("unmark ")) {
                int index = Integer.parseInt(input.substring(7)) - 1;
                Task curr = tasks.get(index);
                curr.setNotDone();
                System.out.println(line + "OK, I've marked this task as not done yet:\n");
                System.out.println(curr + line);
            } else {
                tasks.add(new Task(input));
                System.out.println(line + "added: " + input + "\n" + line);
            }
        }
        sc.close();
    }
}
