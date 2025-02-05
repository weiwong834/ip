package command;

import exceptions.SquidException;
import squid.Ui;
import task.Storage;
import task.TaskList;

import java.time.LocalDate;

public class ShowCommand extends Command{
    private LocalDate date;

    public ShowCommand(LocalDate date) {
        this.date = date;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SquidException {
        ui.showTasksOnDate(date, tasks);
    }
}
