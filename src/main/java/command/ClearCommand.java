package command;

import exceptions.SquidException;
import squid.Ui;
import task.Storage;
import task.TaskList;

/**
 * Represents a command to clear all tasks in the task list.
 * This command handles clearing the current list of tasks
 * and updating the UI and storage to reflect the changes.
 */
public class ClearCommand extends Command{
    /**
     * Executes the clear command which involves clearing all tasks from the task list,
     * saving the empty state to storage, and updating the user interface.
     *
     * @param tasks   The list of tasks to be cleared.
     * @param ui      The user interface component to interact with the user.
     * @param storage The storage component responsible for file operations.
     * @throws SquidException If an error occurs during the clearing or saving process.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SquidException {
        tasks.clear();
        storage.saveTasksToFile(tasks.getAllTasks());
        ui.showTasklistClear();
    }
}
