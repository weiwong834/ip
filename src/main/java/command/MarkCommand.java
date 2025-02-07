package command;

import exceptions.SquidException;
import squid.Ui;
import task.Storage;
import task.Task;
import task.TaskList;

/**
 * Represents a command to mark a task as completed in the task list.
 * This command changes the state of a specified task to 'done'.
 */
public class MarkCommand extends Command{
    private Integer index;

    /**
     * Constructs a MarkCommand with the specified index of the task to be marked as done.
     *
     * @param taskNum The task number in the task list that should be marked as done.
     */
    public MarkCommand(int taskNum) {
        this.index = taskNum - 1;
    }

    /**
     * Executes the command to mark a specific task as done.
     * Validates the index to ensure it is within the task list bounds before marking the task.
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

            tasks.markTask(index);
            Task markedTask = tasks.getTask(index);
            ui.showTaskMark(markedTask, tasks.getSize());
            storage.saveTasksToFile(tasks.getAllTasks());
        } catch (IndexOutOfBoundsException e) {
            throw new SquidException("Invalid index");
        }
    }
}
