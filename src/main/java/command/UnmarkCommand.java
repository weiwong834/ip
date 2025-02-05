package command;

import exceptions.SquidException;
import squid.Ui;
import task.Storage;
import task.Task;
import task.TaskList;

public class UnmarkCommand extends Command{
    private Integer index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SquidException {
        try {
            if (index < 0 || index >= tasks.getSize()) {
                ui.showError("Invalid task number");
                return;
            }

            tasks.unmarkTask(index - 1);
            Task unmarkedTask = tasks.getTask(index - 1);
            ui.showTaskUnmark(unmarkedTask, tasks.getSize());
            storage.saveTasksToFile(tasks.getAllTasks());
        } catch (IndexOutOfBoundsException e) {
            throw new SquidException("Invalid index");
        }
    }
}
