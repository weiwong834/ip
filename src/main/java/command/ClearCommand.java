package command;

import exceptions.SquidException;
import squid.Ui;
import task.Storage;
import task.TaskList;

public class ClearCommand extends Command{
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SquidException {
        tasks.clear();
        storage.saveTasksToFile(tasks.getAllTasks());
        ui.showTasklistClear();
    }
}
