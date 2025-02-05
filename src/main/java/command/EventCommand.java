package command;

import exceptions.SquidException;
import squid.Ui;
import task.Storage;
import task.TaskList;

import java.time.LocalDateTime;

public class EventCommand extends Command{
    private String description;
    private LocalDateTime from;
    private LocalDateTime to;

    public EventCommand(String description, LocalDateTime from, LocalDateTime to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SquidException {

    }
}
