package command;

import exceptions.SquidException;
import squid.Ui;
import task.Storage;
import task.TaskList;

public class ListCommand extends Command{
    public ListCommand() {}

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTasks(tasks.getAllTasks());
    }
}
