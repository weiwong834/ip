package squid;

import task.Deadline;
import task.Event;
import task.Task;
import task.TaskList;
import task.Todo;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Ui {

    public void showGreeting() {
        showLine();
        System.out.println("Hello! I'm Squid\nWhat can I do for you?");
        showLine();
    }

    public void showBye() {
        System.out.println("Bye. Hope to see you again soon!");
        showLine();
    }

    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    public void showError(String message) {
        System.out.println("Error: " + message);
        showLine();
    }

    public void showTasks(List<Task> allTasks) {
        if (allTasks.isEmpty()) {
            System.out.println("You task list is empty!");
            showLine();
        } else {
            System.out.println("Here are your tasks:");
            int index = 1;
            for (Task task : allTasks) {
                System.out.println(index + ". " + task);
                index++;
            }
            showLine();
        }
    }

    public void showTaskDeleted(Task task, int remainingTask) {
        System.out.println("Task removed: " + task);
        System.out.printf("Remaining tasks: " +task);
        showLine();
    }

    public void showLoadingError() {
        System.out.println("Error loading file");
    }

    public String readCommand() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public void showTaskAdded(Task task, int size) {
        System.out.println("Ok! I have added: " + task);
        System.out.println("Now you have " + size + " tasks!");
        showLine();
    }

    public void showTasksOnDate(LocalDate date, TaskList tasks) {
        System.out.println("Task on " + date + ":");
        boolean found = false;
        for (Task task : tasks.getAllTasks()) {
            if (task instanceof Deadline && ((Deadline) task).getBy().toLocalDate().equals(date)) {
                System.out.println(task);
                found = true;
            }
            if (task instanceof Event) {
                Event event = (Event) task;
                if (!event.getFrom().toLocalDate().isAfter(date) && !event.getTo().toLocalDate().isBefore(date)) {
                    System.out.println(task);
                    found = true;
                }
            }
        }
        if (!found) {
            System.out.println("No tasks found on this date");
        }
        showLine();
    }

    public void showTasklistClear() {
        System.out.println("Task list cleared!");
        showLine();
    }

    public void showTaskMark(Task markedTask, int size) {
        System.out.println("Marked as done!");
        showLine();
    }

    public void showTaskUnmark(Task unmarkedTask, int size) {
        System.out.println("Marked as undone");
        showLine();
    }
}
