package command;

import java.time.LocalDateTime;

import squid.Ui;
import task.Event;
import task.Storage;
import task.TaskList;

/**
 * Represents a command to add an event to the task list.
 * This command encapsulates all details necessary to add the event,
 * including its description, start and date.
 */
public class EventCommand extends Command {
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
     * @return A string to indicate an event task is added.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Event newEvent = new Event(description, from, to);
        tasks.addTask(newEvent);
        storage.saveTasksToFile(tasks.getAllTasks());
        return ui.showTaskAdded(newEvent, tasks.getSize());
    }
}
