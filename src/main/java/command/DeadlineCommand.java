package command;

import exceptions.SquidException;
import squid.Ui;
import task.Deadline;
import task.Storage;
import task.TaskList;

import java.time.LocalDateTime;

public class DeadlineCommand extends Command{
    private String description;
    private LocalDateTime by;

    public DeadlineCommand(String description, LocalDateTime by) {
        this.description = description;
        this.by = by;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SquidException {
        if (description.isEmpty() || by == null) {
            throw new SquidException("Invalid deadline command format.");
        }

        Deadline newDeadline = new Deadline(description, by);
        tasks.addTask(newDeadline);
        storage.saveTasksToFile(tasks.getAllTasks());
        ui.showTaskAdded(newDeadline, tasks.getSize());
    }
}
