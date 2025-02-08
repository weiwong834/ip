package command;

import java.time.LocalDate;
import java.util.List;

import exceptions.SquidException;
import squid.Ui;
import task.Event;
import task.Storage;
import task.Task;
import task.TaskList;
import task.Deadline;

/**
 * Represents a command to show tasks that occur on a specific date.
 * This command helps users quickly find tasks that are scheduled for a particular day.
 */
public class ShowCommand extends Command{
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
     * @throws SquidException If the index is invalid or an error occurs during deletion.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SquidException {
        List<Task> allTasks = tasks.getAllTasks();
        boolean isFound = false;

        System.out.println("Task on " + date + ":");

        System.out.println("Tasks on " + date + ":");
        for (Task task : allTasks) {
            if (isTaskOnDate(task)) {
                System.out.println(task);
                isFound = true;
            }
        }

        if (!isFound) {
            System.out.println("No tasks found on this date");
        }

//        ui.showTasksOnDate(date, tasks);
    }

    private boolean isTaskOnDate(Task task) {
        if (task instanceof Deadline
                && ((Deadline) task).getBy().toLocalDate().equals(date)) {
            return true;
        }
        if (task instanceof Event) {
            Event event = (Event) task;
            return !event.getFrom().toLocalDate().isAfter(date)
                    && !event.getTo().toLocalDate().isBefore(date);
        }
        return false;
    }
}
