package command;

import exceptions.SquidException;
import squid.Ui;
import task.Deadline;
import task.Event;
import task.Storage;
import task.TaskList;

import java.time.LocalDateTime;

/**
 * Represents a command to add an event to the task list.
 * This command encapsulates all details necessary to add the event,
 * including its description, start and date.
 */
public class EventCommand extends Command{
    private String description;
    private LocalDateTime from;
    private LocalDateTime to;

    /**
     * Constructs a EventCommand with the specified description and start/ end date time.
     *
     * @param description The text description of the event task.
     * @param from          The date and time by which the task starts.
     * @param to            The date and time by which the task ends.
     */
    public EventCommand(String description, LocalDateTime from, LocalDateTime to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    /**
     * Executes the addition of an event task to the task list, saving the update to storage,
     * and displaying the task addition to the user interface.
     *
     * @param tasks   The list of tasks to which the new event will be added.
     * @param ui      The user interface component to display messages.
     * @param storage The storage component responsible for saving tasks data.
     * @throws SquidException If the description is empty or the event time is null.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SquidException {
        if (description.isEmpty() || from == null || to == null) {
            throw new SquidException("Invalid event command format.");
        }

        Event newEvent = new Event(description, from, to);
        tasks.addTask(newEvent);
        storage.saveTasksToFile(tasks.getAllTasks());
        ui.showTaskAdded(newEvent, tasks.getSize());
    }
}
