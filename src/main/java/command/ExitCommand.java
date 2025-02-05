package command;

import exceptions.SquidException;
import squid.Ui;
import task.Storage;
import task.TaskList;

public class ExitCommand extends Command{
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showBye();
    }
}
