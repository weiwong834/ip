package squid;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import command.ClearCommand;
import command.DeadlineCommand;
import command.DeleteCommand;
import command.EventCommand;
import command.ExitCommand;
import command.ListCommand;
import command.MarkCommand;
import command.ShowCommand;
import command.TodoCommand;
import command.UnmarkCommand;
import exceptions.SquidException;
import command.Command;

public class Parser {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    public static Command parse(String input) throws SquidException {
        String[] words = input.trim().split("\\s+", 2);
        String commandWord = words[0].toLowerCase();
        String args = words.length > 1 ? words[1] : "";

        switch (commandWord) {
        case "list":
            return new ListCommand();
        case "bye":
            return new ExitCommand();
        case "delete":
            if (args.isEmpty()) {
                throw new SquidException("Usage: delete <index>");
            }
            try {
                int index = Integer.parseInt(args);
                return new DeleteCommand(index);
            } catch (NumberFormatException e) {
                throw new SquidException("Usage: delete <index> - Index must be a number.");
            }
        case "mark":
            if (args.isEmpty()) {
                throw new SquidException("Usage: mark <index>");
            }
            try {
                int index = Integer.parseInt(args);
                return new MarkCommand(index);
            } catch (NumberFormatException e) {
                throw new SquidException("Usage: mark <index> - Index must be a number.");
            }
        case "unmark":
            if (args.isEmpty()) {
                throw new SquidException("Usage: unmark <index>");
            }
            try {
                int index = Integer.parseInt(args);
                return new UnmarkCommand(index);
            } catch (NumberFormatException e) {
                throw new SquidException("Usage: unmark <index> - Index must be a number.");
            }
        case "todo":
            if (args.isEmpty()) {
                throw new SquidException("Usage: todo <description>");
            }
            return new TodoCommand(args);
        case "deadline":
            if (!args.contains("/by")) {
                throw new SquidException("Usage: deadline <description> /by <yyyy-mm-dd HHmm>");
            }
            return createDeadlineCommand(args);
        case "event":
            if (!args.contains("/from") || !args.contains("/to")) {
                throw new SquidException("Usage: event <description> /from <yyyy-mm-dd HHmm> /to <yyyy-mm-dd HHmm>");
            }
            return createEventCommand(args);
        case "show":
            try {
                LocalDate date = LocalDate.parse(args, DateTimeFormatter.ISO_LOCAL_DATE);
                return new ShowCommand(date);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please use YYYY-MM-DD.");
            }
        case "clear":
            return new ClearCommand();
        default:
            throw new SquidException("I'm sorry, but I don't know what that means :(");
        }
    }

    private static Command createDeadlineCommand(String args) throws DateTimeParseException{
        if (!args.contains("/by")) {
            System.out.println("Usage: deadline <description> /by <yyyy-mm-dd HHmm>");
        }
        int byIndex = args.indexOf("/by");
        String description = args.substring(0, byIndex).trim();
        String byString = args.substring(byIndex + 4).trim();
        LocalDateTime by = LocalDateTime.parse(byString, formatter);
        return new DeadlineCommand(description, by);
    }

    private static Command createEventCommand(String args) throws DateTimeParseException{
        int fromIndex = args.indexOf("/from");
        int toIndex = args.indexOf("/to");
        String description = args.substring(0, fromIndex).trim();
        String fromString = args.substring(fromIndex + 6, toIndex).trim();
        String toString = args.substring(toIndex + 4).trim();
        LocalDateTime from = LocalDateTime.parse(fromString, formatter);
        LocalDateTime to = LocalDateTime.parse(toString, formatter);
        return new EventCommand(description, from, to);
    }
}
