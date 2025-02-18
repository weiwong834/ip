package command;

import java.util.ArrayList;
import java.util.List;

import squid.Ui;
import task.Storage;
import task.Task;
import task.TaskList;

/**
 * Represents a command to find tasks in the task list that contain the specified keyword in their description.
 * This command filters tasks based on the inclusion of a case-insensitive substring within their descriptions.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Constructs a new FindCommand with the specified keyword.
     *
     * @param keyword The keyword used to filter tasks by their descriptions.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the command to find and list all tasks whose descriptions contain the keyword.
     * The search is case-insensitive, and the results are compiled into a formatted string that
     * lists matching tasks or indicates if no matches are found.
     *
     * @param tasks   The task list from which tasks are searched.
     * @param ui      The user interface component (not used directly in this command).
     * @param storage The storage component (not used directly in this command).
     * @return A formatted string listing all matching tasks or a message indicating no matches.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        List<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks.getAllTasks()) {
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                matchingTasks.add(task);
            }
        }
        if (matchingTasks.isEmpty()) {
            return "No matching tasks found.";
        } else {
            StringBuilder sb = new StringBuilder("Here are the matching tasks in your list:\n");
            for (int i = 0; i < matchingTasks.size(); i++) {
                sb.append((i + 1) + ". " + matchingTasks.get(i) + "\n");
            }
            return sb.toString();
        }
    }
}
