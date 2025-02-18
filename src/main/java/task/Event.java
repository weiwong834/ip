package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a event-type task with a specific start date, due date and time.
 * Each deadline has a description, start date and a due date, and it can be marked as done.
 */
public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Constructs a new Event with the specified description and start/ due date.
     *
     * @param description The description of the event.
     * @param from The date and time the task begins.
     * @param to The date and time the task is due.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Constructs a new Event with the specified description and start/ due date.
     *
     * @param description The description of the event.
     * @param from The date and time the task begins.
     * @param to The date and time the task is due.
     * @param isDone The completion status of the event.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to, boolean isDone) {
        super(description);
        this.from = from;
        this.to = to;
        this.isDone = isDone;
    }

    public LocalDateTime getFrom() {
        return from;
    }

    public LocalDateTime getTo() {
        return to;
    }

    /**
     * Converts the event details to a format suitable for file storage.
     *
     * @return A formatted string representing the event for file storage.
     */
    @Override
    public String toFileFormat() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | "
                + from.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"))
                + " | " + to.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: "
                + from.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")) + " to: "
                + to.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")) + ")";
    }
}
