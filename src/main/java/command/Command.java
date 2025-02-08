package command;

import exceptions.SquidException;
import squid.Ui;
import task.Storage;
import task.TaskList;

/**
 * Represents an abstract command within the application.
 * This class serves as a base for all command types that can be executed.
 * Each specific command will extend this class and implement its execution logic
 * through the {@code execute} method.
 */
 public abstract class Command {
    /**
     * Executes the command with the context in which it is called.
     * This method must be implemented by all subclasses.
     *
     * @param tasks   the list of tasks on which the command operates
     * @param ui      the interface to interact with the user
     * @param storage the storage handling tasks persistence
     * @throws SquidException if an error occurs during command execution
     */
     public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws SquidException;
}
