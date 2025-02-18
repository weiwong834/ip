package command;

import squid.Ui;
import task.Storage;
import task.TaskList;

/**
 * Represents a command to terminate the application.
 * This command handles the termination procedure by triggering bye response.
 */
public class ExitCommand extends Command {
    /**
     * Executes the command to terminate the application.
     * This method will trigger the UI to display a goodbye message.
     *
     * @param tasks   The task list (not used in this command).
     * @param ui      The UI component that displays the goodbye message.
     * @param storage The storage component (not used in this command).
     * @return A string to indicate program termination.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showBye();
    }
}
