package task;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public static Task parse(String line) {
        String[] parts = line.split("\\|");
        for (int i = 0; i < parts.length; i++) {
            parts[i] = parts[i].trim();
        }

        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;

        switch (type) {
            case "T":
                return new Todo(description, isDone);
            case "D":
                LocalDate by = LocalDate.parse(parts[3], formatter);
                return new Deadline(description, by, isDone);
            case "E":
                LocalDate from = LocalDate.parse(parts[3], formatter);
                LocalDate to = LocalDate.parse(parts[4], formatter);
                return new Event(description, from, to, isDone);
            default:
                throw new IllegalArgumentException("Invalid task type: " + type);
        }
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void setDone() {
        isDone = true;
    }

    public void setNotDone () {
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
