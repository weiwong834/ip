package command;

import squid.Ui;
import task.Storage;
import task.TaskList;

/**
 * Represents a command for handling incorrect or malformed user input.
 * This command encapsulates a message that explains the correct usage or the error to the user.
 */
public class IncorrectCommand extends Command{
    private String message;

    /**
     * Constructs an IncorrectCommand with a specific message detailing the error or incorrect usage.
     *
     * @param message The error or usage message to be displayed to the user.
     */
    public IncorrectCommand(String message) {
        this.message = message;
    }

    /**
     * Executes the command by returning a predefined error or usage message.
     * This method does not modify the task list or interact with the storage.
     *
     * @param tasks   The task list (not used in this command).
     * @param ui      The user interface component (not used directly in this command).
     * @param storage The storage component (not used in this command).
     * @return A string containing the error or usage message.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return message;
    }
}
