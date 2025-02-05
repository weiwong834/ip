package command;

import exceptions.SquidException;
import squid.Ui;
import task.Storage;
import task.TaskList;
import task.Todo;

public class TodoCommand extends Command{
    private String description;

    public TodoCommand(String description) {
        this.description = description != null ? description.trim() : "";
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SquidException {
        Todo newTodo = new Todo(description);
        tasks.addTask(newTodo);
        storage.saveTasksToFile(tasks.getAllTasks());
        ui.showTaskAdded(newTodo, tasks.getSize());
    }
}
