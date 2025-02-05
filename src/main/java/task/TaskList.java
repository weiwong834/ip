package task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TaskList {
    private List<Task> tasks;

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

    public int getSize() {
        return tasks.size();
    }

    public void clear() {
        tasks.clear();
    }

    public void markTask(int i) {
        getTask(i).setDone();
    }

    public void unmarkTask(int i) {
        getTask(i).setNotDone();
    }
}
