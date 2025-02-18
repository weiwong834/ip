package command;

import squid.Ui;
import task.Storage;
import task.Task;
import task.TaskList;

/**
 * Represents a command to delete a task from the task list.
 * This command is identified by the index of the task to be deleted.
 */
public class DeleteCommand extends Command {
    private Integer index;

    /**
     * Constructs a DeleteCommand with the specified task index.
     *
     * @param index The index of the task in the task list that should be deleted.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the deletion of a task from the task list based on the index provided,
     * updates the storage with the new list of tasks, and displays the change to the user interface.
     *
     * @param tasks   The list of tasks from which the task will be deleted.
     * @param ui      The user interface component to display messages.
     * @param storage The storage component responsible for saving tasks data.
     * @return A string to indicate task is removed.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task removedTask = tasks.removeTask(index);
        storage.saveTasksToFile(tasks.getAllTasks());
        return ui.showTaskDeleted(removedTask, tasks.getSize());
    }
}
