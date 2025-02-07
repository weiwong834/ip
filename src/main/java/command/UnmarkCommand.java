package command;

import exceptions.SquidException;
import squid.Ui;
import task.Storage;
import task.Task;
import task.TaskList;

/**
 * Represents a command to mark a task as uncompleted in the task list.
 * This command changes the state of a specified task to 'undone'.
 */
public class UnmarkCommand extends Command{
    private Integer index;

    /**
     * Constructs a MarkCommand with the specified index of the task to be marked as done.
     *
     * @param taskNum The task number in the task list that should be marked as undone.
     */
    public UnmarkCommand(int taskNum) {
        this.index = taskNum - 1;
    }

    /**
     * Executes the command to mark a specific task as undone.
     * Validates the index to ensure it is within the task list bounds before unmarking the task.
     * If the task is successfully marked, it updates the UI and saves the updated task list to storage.
     *
     * @param tasks   The list of tasks, one of which will be marked as done.
     * @param ui      The user interface component that will display the outcome of the action.
     * @param storage The storage component that saves the updated task list after marking the task.
     * @throws SquidException If the specified index is invalid, this exception is thrown with a specific error message.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SquidException {
        try {
            if (index < 0 || index >= tasks.getSize()) {
                ui.showError("Invalid task number");
                return;
            }

            tasks.unmarkTask(index);
            Task unmarkedTask = tasks.getTask(index);
            ui.showTaskUnmark(unmarkedTask, tasks.getSize());
            storage.saveTasksToFile(tasks.getAllTasks());
        } catch (IndexOutOfBoundsException e) {
            throw new SquidException("Invalid index");
        }
    }
}
