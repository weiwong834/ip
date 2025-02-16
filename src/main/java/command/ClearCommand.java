package command;

import squid.Ui;
import task.Storage;
import task.TaskList;

/**
 * Represents a command to clear all tasks in the task list.
 * This command handles clearing the current list of tasks
 * and updating the UI and storage to reflect the changes.
 */
public class ClearCommand extends Command {
    /**
     * Executes the clear command which involves clearing all tasks from the task list,
     * saving the empty state to storage, and updating the user interface.
     *
     * @param tasks   The list of tasks to be cleared.
     * @param ui      The user interface component to interact with the user.
     * @param storage The storage component responsible for file operations.
     * @return A string indicating tasks are cleared.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.clear();
        storage.saveTasksToFile(tasks.getAllTasks());
        return ui.showTasklistClear();
    }
}
