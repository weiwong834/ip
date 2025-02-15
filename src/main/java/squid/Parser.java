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
import command.FindCommand;
import command.ListCommand;
import command.MarkCommand;
import command.ShowCommand;
import command.TodoCommand;
import command.UnmarkCommand;
import exceptions.SquidException;
import command.Command;

/**
 * Parses user input into command for execution.
 */
public class Parser {
    public enum CommandType {
        LIST, BYE, DELETE, MARK, UNMARK, TODO, DEADLINE, EVENT, SHOW, CLEAR, FIND
    }

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    /**
     * Parses user input into command.
     *
     * @param input User input string to be parsed.
     * @return Command object corresponding to the user command.
     * @throws SquidException if the command is unknown or the arguments are incorrect.
     */
    public static Command parse(String input) throws SquidException {
        String[] words = input.trim().split("\\s+", 2);
        String commandWord = words[0].toLowerCase();
        String args = words.length > 1 ? words[1] : "";

        CommandType commandType;
        try {
            commandType = CommandType.valueOf(commandWord.toUpperCase());
        }  catch (IllegalArgumentException e) {
            throw new SquidException("idk what that means.");
        }

        switch (commandType) {
        case LIST:
            return new ListCommand();
        case BYE:
            return new ExitCommand();
        case DELETE:
            return parseDeleteCommand(args);
        case MARK:
            return parseMarkCommand(args);
        case UNMARK:
            return parseUnmarkCommand(args);
        case TODO:
            return parseTodoCommand(args);
        case DEADLINE:
            return parseDeadlineCommand(args);
        case EVENT:
            return parseEventCommand(args);
        case SHOW:
            return parseShowCommand(args);
        case CLEAR:
            return new ClearCommand();
        case FIND:
            return parseFindCommand(args);
        default:
            throw new SquidException("I'm sorry, but I don't know what that means :(");
        }
    }

    private static Command parseFindCommand(String args) throws SquidException{
        if (args.isEmpty()) {
            throw new SquidException("Usage: find <keyword>");
        }
        return new FindCommand(args);
    }

    private static Command parseDeleteCommand(String args) throws SquidException {
        if (args.isEmpty()) {
            throw new SquidException("Usage: delete <index>");
        }
        try {
            int index = Integer.parseInt(args);
            return new DeleteCommand(index);
        } catch (NumberFormatException e) {
            throw new SquidException("Usage: delete <index> - Index must be a number.");
        }
    }

    private static Command parseMarkCommand(String args) throws SquidException{
        if (args.isEmpty()) {
            throw new SquidException("Usage: mark <index>");
        }
        try {
            int index = Integer.parseInt(args);
            return new MarkCommand(index);
        } catch (NumberFormatException e) {
            throw new SquidException("Usage: mark <index> - Index must be a number.");
        }
    }

    private static Command parseUnmarkCommand(String args) throws SquidException{
        if (args.isEmpty()) {
            throw new SquidException("Usage: unmark <index>");
        }
        try {
            int index = Integer.parseInt(args);
            return new UnmarkCommand(index);
        } catch (NumberFormatException e) {
            throw new SquidException("Usage: unmark <index> - Index must be a number.");
        }
    }

    private static Command parseTodoCommand(String args) throws SquidException{
        if (args.isEmpty()) {
            throw new SquidException("Usage: todo <description>");
        }
        return new TodoCommand(args);
    }

    private static Command parseDeadlineCommand(String args) throws DateTimeParseException{
        if (!args.contains("/by")) {
            System.out.println("Usage: deadline <description> /by <yyyy-mm-dd HHmm>");
        }
        int byIndex = args.indexOf("/by");
        String description = args.substring(0, byIndex).trim();
        String byString = args.substring(byIndex + 4).trim();
        LocalDateTime by = LocalDateTime.parse(byString, formatter);
        return new DeadlineCommand(description, by);
    }

    private static Command parseEventCommand(String args) throws SquidException, DateTimeParseException{
        if (!args.contains("/from") || !args.contains("/to")) {
            throw new SquidException("Usage: event <description> /from <yyyy-mm-dd HHmm> /to <yyyy-mm-dd HHmm>");
        }
        int fromIndex = args.indexOf("/from");
        int toIndex = args.indexOf("/to");
        String description = args.substring(0, fromIndex).trim();
        String fromString = args.substring(fromIndex + 6, toIndex).trim();
        String toString = args.substring(toIndex + 4).trim();
        LocalDateTime from = LocalDateTime.parse(fromString, formatter);
        LocalDateTime to = LocalDateTime.parse(toString, formatter);
        return new EventCommand(description, from, to);
    }

    private static Command parseShowCommand(String args) throws SquidException, DateTimeParseException {
        try {
            LocalDate date = LocalDate.parse(args, DateTimeFormatter.ISO_LOCAL_DATE);
            return new ShowCommand(date);
        } catch (DateTimeParseException e) {
            throw new SquidException("Invalid date format. Please use YYYY-MM-DD.");
        }
    }
}