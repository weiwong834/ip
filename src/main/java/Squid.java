import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Squid {
    public enum CommandType {
        BYE, LIST, DELETE, TODO, DEADLINE, EVENT, MARK, UNMARK, SHOW
    }

    public static void main(String[] args) throws SquidException {
        Scanner sc = new Scanner(System.in);
        TaskStorage storage = new TaskStorage("./data/squid.txt");
        List<Task> tasks = storage.loadTasksFromFile();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

        String line = "____________________________________________________________\n";
        String greeting = " Hello! I'm Squid\n"
                        + " What can I do for you?\n";
        String goodbye = " Bye. Hope to see you again soon!\n";


        System.out.println(line + greeting + line); // print greetings

        while (true) {
            String input = sc.nextLine().trim();
            String[] parts = input.split(" ", 2);
            String commandString = parts[0].toUpperCase();
            CommandType command;

            try {
                command = CommandType.valueOf(commandString);
            } catch (IllegalArgumentException e) {
                System.out.println(line + "what you talking about...\n" + line);
                continue;
            }

            switch (command) {
                case BYE: {
                    storage.saveTasksToFile(tasks);
                    System.out.println(line + goodbye + line);
                    return;
                }
                case LIST: {
                    if (tasks.isEmpty()) {
                        throw new SquidException("you have no tasks");
                    }
                    System.out.println(line + "Here are the tasks in your list:\n");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println((i + 1) + ". " + tasks.get(i) + "\n");
                    }
                    System.out.println(line);
                    break;
                }
                case SHOW: {
                    if (parts.length < 2) {
                        throw new SquidException("Usage: show (YYYY-MM-DD)");
                    }
                    LocalDate queryDate = LocalDate.parse(parts[1], DateTimeFormatter.ISO_LOCAL_DATE);
                    System.out.println("Tasks on " + queryDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ":\n");
                    boolean found = false;
                    for (Task task : tasks) {
                        if (task instanceof Deadline && ((Deadline) task).getBy().toLocalDate().equals(queryDate)) {
                            System.out.println(task);
                            found = true;
                        }
                        if (task instanceof Event) {
                            Event event = (Event) task;
                            if (!event.getFrom().toLocalDate().isAfter(queryDate) && !event.getTo().toLocalDate().isBefore(queryDate)) {
                                System.out.println(task);
                                found = true;
                            }
                        }
                    }
                    if (!found) {
                        System.out.println("No tasks found on this date");
                    }
                    System.out.println(line);
                    break;
                }
                case DELETE: {
                    if (parts.length < 2) {
                        throw new SquidException("Usage: delete (task number)");
                    }
                    int index = Integer.parseInt(parts[1].trim()) - 1;
                    if (index < 0 || index >= tasks.size()) {
                        throw new SquidException("Invalid task number");
                    }
                    Task removedTask = tasks.remove(index);
                    storage.saveTasksToFile(tasks);
                    System.out.println(line + "Noted. I've removed this task:\n" + removedTask + "\nNow you have "
                            + tasks.size() + " tasks in the list.\n" + line);
                    break;
                }
                case TODO: {
                    if (parts.length < 2) {
                        throw new SquidException("Usage: todo (description)");
                    }
                    String description = parts[1].trim();
                    tasks.add(new Todo(description));
                    storage.saveTasksToFile(tasks);
                    System.out.println(line + "Got it. I've added this task:\n" + new Todo(description) + "\nNow you have "
                            + tasks.size() + " tasks in the list.\n" + line);
                    break;
                }
                case DEADLINE: {
                    if (parts.length < 2 || !parts[1].contains("/by")) {
                        throw new SquidException("Usage: deadline (description) /by (yyyy-mm-dd HHmm)");
                    }
                    int byIndex = parts[1].indexOf("/by");
                    String description = parts[1].substring(0, byIndex).trim();
                    String byString = parts[1].substring(byIndex + 4).trim();
                    LocalDateTime by = LocalDateTime.parse(byString, formatter);
                    tasks.add(new Deadline(description, by));
                    storage.saveTasksToFile(tasks);
                    System.out.println(line + "Got it. I've added this task:\n" + new Deadline(description, by) + "\nNow you have "
                            + tasks.size() + " tasks in the list.\n" + line);
                    break;
                }
                case EVENT: {
                    if (parts.length < 2 || !parts[1].contains("/from") || !parts[1].contains("/to")) {
                        throw new SquidException("Usage: event (description) /from (yyyy-mm-dd HHmm) /to (yyyy-mm-dd HHmm)");
                    }
                    int fromIndex = parts[1].indexOf("/from");
                    int toIndex = parts[1].indexOf("/to");
                    String description = parts[1].substring(0, fromIndex).trim();
                    String fromString = parts[1].substring(fromIndex + 6, toIndex).trim();
                    String toString = parts[1].substring(toIndex + 4).trim();
                    LocalDateTime from = LocalDateTime.parse(fromString, formatter);
                    LocalDateTime to = LocalDateTime.parse(toString, formatter);
                    tasks.add(new Event(description, from, to));
                    storage.saveTasksToFile(tasks);
                    System.out.println(line + "Got it. I've added this task:\n" + new Event(description, from, to) + "\nNow you have "
                            + tasks.size() + " tasks in the list.\n" + line);
                    break;
                }
                case MARK: {
                    if (parts.length < 2) {
                        throw new SquidException("Usage: mark (task number)");
                    }
                    int index = Integer.parseInt(parts[1].trim()) -1;
                    if (index < 0 || index >= tasks.size()) {
                        throw new SquidException("Inaccessible index");
                    }
                    Task curr = tasks.get(index);
                    curr.setDone();
                    storage.saveTasksToFile(tasks);
                    System.out.println(line + "Nice! I've marked this task as done:\n");
                    System.out.println(curr + "\n" + line);
                    break;
                }
                case UNMARK: {
                    if (parts.length < 2) {
                        throw new SquidException("Usage: unmark (task number)");
                    }
                    int index = Integer.parseInt(parts[1].trim()) -1;
                    if (index < 0 || index >= tasks.size()) {
                        throw new SquidException("Inaccessible index");
                    }
                    Task curr = tasks.get(index);
                    curr.setNotDone();
                    storage.saveTasksToFile(tasks);
                    System.out.println(line + "OK, I've marked this task as not done yet:\n");
                    System.out.println(curr + "\n" + line);
                    break;
                }
                default:
                    throw new SquidException("what you talking about...");

            }

        }
    }
}
