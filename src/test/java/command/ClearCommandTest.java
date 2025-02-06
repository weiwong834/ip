package command;

import exceptions.SquidException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import squid.Ui;
import task.Storage;
import task.TaskList;
import task.Todo;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClearCommandTest {

    private TaskList tasks;
    private Ui ui;
    private Storage storage;
    private ClearCommand command;

    @BeforeEach
    public void setUp() {
        ui = new Ui();
        tasks = new TaskList();
        storage = new Storage("data/squid.txt");

        tasks.addTask(new Todo("Dummy task 1"));
        tasks.addTask(new Todo("Dummy task 2"));

        command = new ClearCommand();
    }

    @Test
    public void execute_shouldClearTasks() throws SquidException {
        assertEquals(2, tasks.getSize());

        command.execute(tasks, ui, storage);

        assertEquals(0, tasks.getSize());
    }
}

