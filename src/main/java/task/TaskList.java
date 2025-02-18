package task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import exceptions.AlreadyMarkedException;
import exceptions.AlreadyUnmarkedException;

/**
 * Represents a list of tasks with various operations to manipulate tasks.
 * This class supports addition, removal, and access to tasks based on index positions.
 * It provides functionality to mark or unmark a task as done.
 */
public class TaskList {
    private static List<Task> tasks;

    /**
     * Initializes an empty list of tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Initializes the task list with an existing list of tasks.
     *
     * @param tasks The initial list of tasks.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task removeTask(int index) {
        return tasks.remove(index);
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public List<Task> getAllTasks() {
        return Collections.unmodifiableList(tasks);
    }

    public static int getSize() {
        return tasks.size();
    }

    public void clear() {
        tasks.clear();
    }

    /**
     * Marks a specified task as done.
     *
     * @param index The index of the task to be marked as done.
     * @throws AlreadyMarkedException if the task is already marked as done.
     */
    public void markTask(int index) throws AlreadyMarkedException {
        Task task = tasks.get(index);
        if (task.isDone()) {
            throw new AlreadyMarkedException("Task already marked as done");
        }
        task.setDone();
    }

    /**
     * Unmarks a specified task as not done.
     *
     * @param index The index of the task to be unmarked.
     * @throws AlreadyUnmarkedException if the task is already unmarked.
     */
    public void unmarkTask(int index) throws AlreadyUnmarkedException {
        Task task = tasks.get(index);
        if (!task.isDone()) {
            throw new AlreadyUnmarkedException("Task already marked as done");
        }
        task.setNotDone();
    }
}
