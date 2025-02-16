package task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import exceptions.AlreadyMarkedException;
import exceptions.AlreadyUnmarkedException;

public class TaskList {
    private static List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

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

    public void markTask(int index) throws AlreadyMarkedException {
        Task task = tasks.get(index);
        if (task.isDone()) {
            throw new AlreadyMarkedException("Task already marked as done");
        }
        task.setDone();
    }

    public void unmarkTask(int index) throws AlreadyUnmarkedException {
        Task task = tasks.get(index);
        if (!task.isDone()) {
            throw new AlreadyUnmarkedException("Task already marked as done");
        }
        task.setNotDone();
    }
}
