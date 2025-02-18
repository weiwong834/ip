package command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import squid.Ui;
import task.Storage;
import task.TaskList;
import task.Todo;

/**
 * Tests for the ClearCommand class to ensure tasks are cleared.
 */
public class ClearCommandTest {

    private TaskList tasks;
    private Ui ui;
    private Storage storage;
    private ClearCommand command;

    /**
     * Sets up the environment for each test.
     */
    @BeforeEach
    public void setUp() {
        ui = new Ui();
        tasks = new TaskList();
        storage = new Storage("data/squid.txt");

        tasks.addTask(new Todo("Dummy task 1"));
        tasks.addTask(new Todo("Dummy task 2"));

        command = new ClearCommand();
    }

    /**
     * Tests that a task list is empty when clear command is executed.
     */
    @Test
    public void execute_shouldClearTasks() {
        assertEquals(2, tasks.getSize());

        command.execute(tasks, ui, storage);

        assertEquals(0, tasks.getSize());
    }
}

