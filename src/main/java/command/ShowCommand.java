package command;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import squid.Ui;
import task.Deadline;
import task.Event;
import task.Storage;
import task.Task;
import task.TaskList;

/**
 * Represents a command to show tasks that occur on a specific date.
 * This command helps users quickly find tasks that are scheduled for a particular day.
 */
public class ShowCommand extends Command {
    private LocalDate date;

    /**
     * Constructs a ShowCommand with the specified task index.
     *
     * @param date The date t
     */
    public ShowCommand(LocalDate date) {
        this.date = date;
    }

    /**
     * Executes the command to show task on a specific date,
     * This method display the tasks and interacts with the task list to gather the required tasks.
     *
     * @param tasks   The list of tasks from which the task will be deleted.
     * @param ui      The user interface component to display messages.
     * @param storage The storage component responsible for saving tasks data.
     * @return A formatted string listing all matching tasks or a message indicating no matches.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        List<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks.getAllTasks()) {
            if (isTaskOnDate(task)) {
                matchingTasks.add(task);
            }
        }

        if (matchingTasks.isEmpty()) {
            return "No tasks found on this date";
        } else {
            StringBuilder sb = new StringBuilder("Task on " + date + ":\n");
            for (int i = 0; i < matchingTasks.size(); i++) {
                sb.append((i + 1) + ". " + matchingTasks.get(i) + "\n");
            }
            return sb.toString();
        }
    }

    private boolean isTaskOnDate(Task task) {
        boolean isDeadlineOnDate = task instanceof Deadline
                && ((Deadline) task).getBy().toLocalDate().equals(date);

        boolean isEventOnDate = false;
        if (task instanceof Event) {
            Event event = (Event) task;
            boolean startsBeforeOrOnDate = !event.getFrom().toLocalDate().isAfter(date);
            boolean endsAfterOrOnDate = !event.getTo().toLocalDate().isBefore(date);
            isEventOnDate = startsBeforeOrOnDate && endsAfterOrOnDate;
        }

        return isDeadlineOnDate || isEventOnDate;
    }
}
