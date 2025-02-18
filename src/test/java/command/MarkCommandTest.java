package command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import squid.Ui;
import task.Storage;
import task.TaskList;
import task.Todo;

/**
 * Tests for the MarkCommand class to ensure tasks are marked correctly and errors are handled.
 */
public class MarkCommandTest {

    private TaskList tasks;
    private Ui ui;
    private Storage storage;
    private MarkCommand command;
    private int taskIndexToMark;

    /**
     * Sets up the environment for each test.
     */
    @BeforeEach
    public void setUp() {
        ui = new Ui();
        tasks = new TaskList();
        storage = new Storage("data/squid.txt");

        tasks.addTask(new Todo("Task 1"));
        tasks.addTask(new Todo("Task 2"));
        taskIndexToMark = 1;

        command = new MarkCommand(taskIndexToMark);
    }

    /**
     * Tests that a task is correctly marked as done when the mark command is executed.
     */
    @Test
    public void execute_marksTaskAsDone() {
        assertFalse(tasks.getTask(taskIndexToMark).isDone());

        command.execute(tasks, ui, storage);

        assertTrue(tasks.getTask(taskIndexToMark).isDone());
    }

    /**
     * Tests that the correct error message is returned when trying to mark an already marked task.
     */
    @Test
    public void execute_attemptToMarkAlreadyDoneTask_returnsErrorMessage() {
        tasks.getTask(taskIndexToMark).setDone();

        String result = command.execute(tasks, ui, storage);

        assertEquals("Error: Already marked previously", result);
    }
}
