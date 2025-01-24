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
            try {
                String input = sc.nextLine();

                if (input.equals("bye")) {
                    System.out.println(line + goodbye + line);
                    break;
                } else if (input.equals("list")) {
                    if (tasks.isEmpty()) {
                        throw new SquidException("you have no tasks");
                    }
                    System.out.println(line + "Here are the tasks in your list:\n");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println((i + 1) + ". " + tasks.get(i) + "\n");
                    }
                    System.out.println(line);
                } else if (input.startsWith("delete ")) {
                    int index = Integer.parseInt(input.substring(7).trim()) - 1;
                    if (index < 0 || index >= tasks.size()) {
                        throw new SquidException("Invalid task number");
                    }
                    Task removedTask = tasks.remove(index);
                    System.out.println(line + "Noted. I've removed this task:\n" + removedTask + "\nNow you have "
                            + tasks.size() + " tasks in the list.\n" + line);
                } else if (input.startsWith("todo ")) {
                    if (input.trim().length() <= 5) {
                        throw new SquidException("Usage: todo (description)");
                    }
                    String description = input.substring(5).trim();
                    tasks.add(new Todo(description));
                    System.out.println(line + "Got it. I've added this task:\n" + new Todo(description) + "\nNow you have "
                            + tasks.size() + " tasks in the list.\n" + line);
                } else if (input.startsWith("deadline ")) {
                    int bySign = input.indexOf("/by");
                    if (input.trim().length() <= 9 || bySign == -1) {
                        throw new SquidException("Usage: deadline (description) /by (deadline)");
                    }
                    String description = input.substring(9, bySign).trim();
                    String by = input.substring(bySign + 4).trim();
                    tasks.add(new Deadline(description, by));
                    System.out.println(line + "Got it. I've added this task:\n" + new Deadline(description, by) + "\nNow you have "
                            + tasks.size() + " tasks in the list.\n" + line);
                } else if (input.startsWith("event ")) {
                    int fromSign = input.indexOf("/from");
                    int toSign = input.indexOf("/to");
                    if (input.trim().length() <= 9 || fromSign == -1 || toSign == -1) {
                        throw new SquidException("Usage: event (description) /from (start) /to (end)");
                    }
                    String description = input.substring(6, fromSign).trim();
                    String from = input.substring(fromSign + 6, toSign).trim();
                    String to = input.substring(toSign + 4).trim();
                    tasks.add(new Event(description, from, to));
                    System.out.println(line + "Got it. I've added this task:\n" + new Event(description, from, to) + "\nNow you have "
                            + tasks.size() + " tasks in the list.\n" + line);
                } else if (input.startsWith("mark ")) {
                    int index = Integer.parseInt(input.substring(5)) - 1;
                    if (index < 0 || index >= tasks.size()) {
                        throw new SquidException("Inaccessible index");
                    }
                    Task curr = tasks.get(index);
                    curr.setDone();
                    System.out.println(line + "Nice! I've marked this task as done:\n");
                    System.out.println(curr + "\n" + line);
                } else if (input.startsWith("unmark ")) {
                    int index = Integer.parseInt(input.substring(7)) - 1;
                    if (index < 0 || index >= tasks.size()) {
                        throw new SquidException("Inaccessible index");
                    }
                    Task curr = tasks.get(index);
                    curr.setNotDone();
                    System.out.println(line + "OK, I've marked this task as not done yet:\n");
                    System.out.println(curr + "\n" + line);
                } else {
                    throw new SquidException("what you talking about...");
                }
            } catch (SquidException e) {
                System.out.println(line + e.getMessage() + "\n" + line);
            }
        }
        sc.close();
    }
}
