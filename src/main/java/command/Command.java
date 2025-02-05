package command;

import exceptions.SquidException;
import squid.Ui;
import task.Storage;
import task.TaskList;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws SquidException;
}
