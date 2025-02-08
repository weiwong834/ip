package command;

import java.util.ArrayList;
import java.util.List;

import exceptions.SquidException;
import squid.Ui;
import task.Storage;
import task.Task;
import task.TaskList;

public class FindCommand extends Command{
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SquidException {
        List<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks.getAllTasks()) {
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                matchingTasks.add(task);
            }
        }
        if (matchingTasks.isEmpty()) {
            System.out.println("No matching tasks found.");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < matchingTasks.size(); i++) {
                System.out.println((i + 1) + ". " + matchingTasks.get(i));
            }
        }
    }
}
