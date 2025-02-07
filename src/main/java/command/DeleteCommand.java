package command;

import exceptions.SquidException;
import squid.Ui;
import task.Storage;
import task.Task;
import task.TaskList;

/**
 * Represents a command to delete a task from the task list.
 * This command is identified by the index of the task to be deleted.
 */
public class DeleteCommand extends Command{
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
     * @throws SquidException If the index is invalid or an error occurs during deletion.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SquidException{
        try {
            if (index < 0 || index > tasks.getSize()) {
                ui.showError("Invalid task number");
                return;
            }

            Task removedTask = tasks.removeTask(index - 1);
            ui.showTaskDeleted(removedTask, tasks.getSize());
            storage.saveTasksToFile(tasks.getAllTasks());
        } catch (IndexOutOfBoundsException e) {
            throw new SquidException("Invalid index");
        }
    }
}
