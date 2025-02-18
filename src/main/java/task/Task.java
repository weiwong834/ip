package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a generic task with common properties and behavior across different types of tasks.
 * This class is the superclass for more specific tasks such as Todo, Deadline, and Event.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a new Task with the specified description.
     * Initially, the task is not completed.
     *
     * @param description The description of the task, not null.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Parses a formatted string to create an appropriate Task subclass object.
     * where Type is 'T' for Todo, 'D' for Deadline, or 'E' for Event.
     *
     * @param line The task data in file format.
     * @return The specific Task object (Todo, Deadline, Event) based on the provided string.
     * @throws IllegalArgumentException if the task type is unknown or the string format is incorrect.
     */
    public static Task parse(String line) {
        String[] parts = line.split("\\|");
        for (int i = 0; i < parts.length; i++) {
            parts[i] = parts[i].trim();
        }

        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

        switch (type) {
        case "T":
            return new Todo(description, isDone);
        case "D":
            LocalDateTime by = LocalDateTime.parse(parts[3], formatter);
            return new Deadline(description, by, isDone);
        case "E":
            LocalDateTime from = LocalDateTime.parse(parts[3], formatter);
            LocalDateTime to = LocalDateTime.parse(parts[4], formatter);
            return new Event(description, from, to, isDone);
        default:
            throw new IllegalArgumentException("Invalid task type: " + type);
        }
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone() {
        isDone = true;
    }

    public void setNotDone() {
        isDone = false;
    }

    public String toFileFormat() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
