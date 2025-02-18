package squid;

import java.util.List;

import task.Task;

/**
 * Handles user interactions by providing methods to format and return responses as strings.
 * Each method in this class prepares a specific type of message to be displayed to the user.
 */
public class Ui {

    public static String showGreeting() {
        return "Hello! I'm Squid\nWhat can I do for you?";
    }

    public String showBye() {
        return "Bye!";
    }

    public String showError(String message) {
        return "Error: " + message;
    }

    public String showTasks(List<Task> allTasks) {
        if (allTasks.isEmpty()) {
            return "Your task list is empty !";
        } else {
            StringBuilder sb = new StringBuilder("Here are your tasks:\n");
            int index = 1;
            for (Task task : allTasks) {
                sb.append(index + ". " + task + "\n");
                index++;
            }
            return sb.toString();
        }
    }

    public String showTaskDeleted(Task task, int remainingTask) {
        StringBuilder sb = new StringBuilder("Task removed:\n" + task + "\n");
        sb.append("Remaining tasks: " + remainingTask);
        return sb.toString();
    }

    public String showTaskAdded(Task task, int size) {
        StringBuilder sb = new StringBuilder("Ok! I have added:\n" + task + "\n");
        sb.append("Now you have " + size + " tasks!");
        return sb.toString();
    }

    public String showTasklistClear() {
        return "Task list cleared!";
    }

    public String showTaskMark() {
        return "Marked as done!";
    }

    public String showTaskUnmark() {
        return "Marked as undone";
    }
}
