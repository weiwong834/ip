package command;

import exceptions.SquidException;
import squid.Ui;
import task.Deadline;
import task.Storage;
import task.TaskList;

import java.time.LocalDateTime;

/**
 * Represents a command to add a deadline to the task list.
 * This command encapsulates all details necessary to add the deadline,
 * including its description and due date.
 */
public class DeadlineCommand extends Command{
    private String description;
    private LocalDateTime by;

    /**
     * Constructs a DeadlineCommand with the specified description and due date time.
     *
     * @param description The text description of the deadline task.
     * @param by          The date and time by which the task is due.
     */
    public DeadlineCommand(String description, LocalDateTime by) {
        this.description = description;
        this.by = by;
    }

    /**
     * Executes the addition of a deadline task to the task list, saving the update to storage,
     * and displaying the task addition to the user interface.
     *
     * @param tasks   The list of tasks to which the new deadline will be added.
     * @param ui      The user interface component to display messages.
     * @param storage The storage component responsible for saving tasks data.
     * @throws SquidException If the description is empty or the deadline time is null.
     */
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
