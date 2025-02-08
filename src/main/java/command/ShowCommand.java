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

public class ShowCommand extends Command{
    private LocalDate date;

    public ShowCommand(LocalDate date) {
        this.date = date;
    }

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
