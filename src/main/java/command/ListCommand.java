package command;

import squid.Ui;
import task.Storage;
import task.TaskList;

/**
 * Represents a command to list all tasks in the task manager.
 * This command allows the user to view all tasks currently stored in the task list.
 */
public class ListCommand extends Command{
    /**
     * Executes the command to display all tasks in the task list.
     * This method invokes the UI to show all tasks.
     *
     * @param tasks   The task list whose tasks are to be displayed.
     * @param ui      The UI component that will display the tasks.
     * @param storage The storage component (not used in this command).
     * @return A formatted string of the current list of tasks.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showTasks(tasks.getAllTasks());
    }
}
