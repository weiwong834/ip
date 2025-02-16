package squid;

import command.Command;
import exceptions.SquidException;
import task.Storage;
import task.TaskList;

/**
 * The Squid class manages the overall workflow.
 */
public class Squid {
    private Storage storage;
    private TaskList tasks;
    protected Ui ui;

    /**
     * Constructs a new instance of Squid which sets up the user interface, storage,
     * and task list from a specified file path.
     *
     * @param filePath the relative or absolute path to the data file where tasks are stored.
     */
    public Squid(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.loadTasksFromFile());
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            return command.execute(tasks, ui, storage);
        } catch (SquidException e) {
            throw new RuntimeException(e);
        }
    }
}
