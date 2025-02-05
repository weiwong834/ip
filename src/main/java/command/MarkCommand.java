package command;

import exceptions.SquidException;
import squid.Ui;
import task.Storage;
import task.Task;
import task.TaskList;

public class MarkCommand extends Command{
    private Integer index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SquidException {
        try {
            if (index < 0 || index >= tasks.getSize()) {
                ui.showError("Invalid task number");
                return;
            }

            tasks.markTask(index - 1);
            Task markedTask = tasks.getTask(index - 1);
            ui.showTaskMark(markedTask, tasks.getSize());
            storage.saveTasksToFile(tasks.getAllTasks());
        } catch (IndexOutOfBoundsException e) {
            throw new SquidException("Invalid index");
        }
    }
}
