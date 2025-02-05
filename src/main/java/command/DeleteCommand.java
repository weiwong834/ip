package command;

import exceptions.SquidException;
import squid.Ui;
import task.Storage;
import task.Task;
import task.TaskList;


public class DeleteCommand extends Command{
    private Integer index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SquidException{
        try {
            if (index < 0 || index >= tasks.getSize()) {
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
