package command;

import exceptions.SquidException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import squid.Ui;
import task.Storage;
import task.TaskList;
import task.Todo;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class MarkCommandTest {

    private TaskList tasks;
    private Ui ui;
    private Storage storage;
    private MarkCommand command;
    private int taskIndexToMark;

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

    @Test
    public void execute_marksTaskAsDone() throws SquidException {
        assertFalse(tasks.getTask(taskIndexToMark).isDone());

        command.execute(tasks, ui, storage);

        assertTrue(tasks.getTask(taskIndexToMark - 1).isDone());
    }
}

