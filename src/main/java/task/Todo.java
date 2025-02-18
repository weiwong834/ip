package task;

/**
 * Represents a todo-type task.
 * Each todo has a description  and it can be marked as done.
 */
public class Todo extends Task {
    /**
     * Constructs a new Todo with the specified description.
     *
     * @param description The description of the deadline.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Constructs a new Todo with the specified description.
     *
     * @param description The description of the todo.
     * @param isDone The completion status of the todo.
     */
    public Todo(String description, boolean isDone) {
        super(description);
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
