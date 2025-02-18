package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline-type task with a specific due date and time.
 * Each deadline has a description and a due date, and it can be marked as done.
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Constructs a new Deadline with the specified description and due date.
     *
     * @param description The description of the deadline.
     * @param by The date and time the task is due.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Constructs a new Deadline with the specified description, due date, and completion status.
     *
     * @param description The description of the deadline.
     * @param by The date and time the task is due.
     * @param isDone The completion status of the deadline.
     */
    public Deadline(String description, LocalDateTime by, boolean isDone) {
        super(description);
        this.by = by;
        this.isDone = isDone;
    }

    public LocalDateTime getBy() {
        return by;
    }

    /**
     * Converts the deadline details to a format suitable for file storage.
     *
     * @return A formatted string representing the deadline for file storage.
     */
    @Override
    public String toFileFormat() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | "
                + by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + by.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")) + ")";
    }
}
