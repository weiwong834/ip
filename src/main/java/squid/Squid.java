package squid;

import command.Command;
import command.ExitCommand;
import exceptions.SquidException;
import task.*;

public class Squid {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Squid(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.loadTasksFromFile());
    }

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

    public static void main(String[] args) {
        new Squid("data/squid.txt").run();
    }
}
