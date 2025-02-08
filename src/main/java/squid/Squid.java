package squid;

import command.Command;
import command.ExitCommand;
import exceptions.SquidException;
import task.Storage;
import task.TaskList;

/**
 * The Squid class is the main class of the application that manages the overall
 * workflow, including initialization and the main execution loop.
 */
public class Squid {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

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
     * Starts the application and runs the main program loop.
     * This method continuously reads commands from the user, processes and executes them until an exit command.
     */
    public void run() {
        ui.showGreeting();
        boolean isRunning = true;
        while (isRunning) {
            String input = ui.readCommand();
            try {
                Command command = Parser.parse(input);
                command.execute(tasks, ui, storage);
                isRunning = !(command instanceof ExitCommand);
            } catch (SquidException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    /**
     * The main entry point for the application to run the Squid application.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        new Squid("data/squid.txt").run();
    }
}
