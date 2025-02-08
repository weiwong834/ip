package command;

import exceptions.SquidException;
import squid.Ui;
import task.Storage;
import task.TaskList;
import task.Todo;


/**
 * Represents a command to add a todo to the task list.
 * This command encapsulates all details necessary to add the todo
 */
public class TodoCommand extends Command{
    private String description;

    /**
     * Constructs a TodoCommand with the specified description.
     *
     * @param description The text description of the todo task.
     */
    public TodoCommand(String description) {
        this.description = description != null ? description.trim() : "";
    }

    /**
     * Executes the addition of a todo task to the task list, saving the update to storage,
     * and displaying the task addition to the user interface.
     *
     * @param tasks   The list of tasks to which the new todo will be added.
     * @param ui      The user interface component to display messages.
     * @param storage The storage component responsible for saving tasks data.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Todo newTodo = new Todo(description);
        tasks.addTask(newTodo);
        storage.saveTasksToFile(tasks.getAllTasks());
        ui.showTaskAdded(newTodo, tasks.getSize());
    }
}
