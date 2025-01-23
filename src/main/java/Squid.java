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
            } else if (input.startsWith("todo")) {
                String description = input.substring(5).trim();
                tasks.add(new Todo(description));
                System.out.println(line + "Got it. I've added this task:\n" + new Todo(description) + "\nNow you have "
                        + tasks.size() + " tasks in the list.\n" + line);
            } else if (input.startsWith("deadline")) {
                int bySign = input.indexOf("/by");
                String description = input.substring(9, bySign).trim();
                String by = input.substring(bySign + 4).trim();
                tasks.add(new Deadline(description, by));
                System.out.println(line + "Got it. I've added this task:\n" + new Deadline(description, by) + "\nNow you have "
                        + tasks.size() + " tasks in the list.\n" + line);
            } else if (input.startsWith("event")) {
                int fromSign = input.indexOf("/from");
                int toSign = input.indexOf("/to");
                String description = input.substring(6, fromSign).trim();
                String from = input.substring(fromSign + 6, toSign).trim();
                String to = input.substring(toSign + 4).trim();
                tasks.add(new Event(description, from, to));
                System.out.println(line + "Got it. I've added this task:\n" + new Event(description, from, to) + "\nNow you have "
                        + tasks.size() + " tasks in the list.\n" + line);
            } else if (input.startsWith("mark ")) {
                int index = Integer.parseInt(input.substring(5)) - 1;
                Task curr = tasks.get(index);
                curr.setDone();
                System.out.println(line + "Nice! I've marked this task as done:\n");
                System.out.println(curr + "\n" + line);
            } else if (input.startsWith("unmark ")) {
                int index = Integer.parseInt(input.substring(7)) - 1;
                Task curr = tasks.get(index);
                curr.setNotDone();
                System.out.println(line + "OK, I've marked this task as not done yet:\n");
                System.out.println(curr + "\n" + line);
            }
        }
        sc.close();
    }
}
