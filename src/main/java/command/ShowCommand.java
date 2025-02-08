package command;

import exceptions.SquidException;
import squid.Ui;
import task.Storage;
import task.TaskList;

import java.time.LocalDate;

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
        ui.showTasksOnDate(date, tasks);
    }
}
