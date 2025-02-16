package squid;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Objects;

import command.ClearCommand;
import command.DeadlineCommand;
import command.DeleteCommand;
import command.EventCommand;
import command.ExitCommand;
import command.FindCommand;
import command.IncorrectCommand;
import command.ListCommand;
import command.MarkCommand;
import command.ShowCommand;
import command.TodoCommand;
import command.UnmarkCommand;
import exceptions.SquidException;
import command.Command;
import task.TaskList;

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
        assert !Objects.equals(input, "") : "Input should not be null";
        
        String[] words = input.trim().split("\\s+", 2);
        String commandWord = words[0].toLowerCase();
        String args = words.length > 1 ? words[1] : "";

        CommandType commandType;
        try {
            commandType = CommandType.valueOf(commandWord.toUpperCase());
        }  catch (IllegalArgumentException e) {
            return new IncorrectCommand("idk what that means :(");
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
            return new IncorrectCommand("I'm sorry, but I don't know what that means :(");
        }
    }

    private static Command parseFindCommand(String args) {
        if (args.isEmpty()) {
            return new IncorrectCommand("Usage: find <keyword>");
        }
        return new FindCommand(args);
    }

    private static Command parseDeleteCommand(String args) {
        if (args.isEmpty()) {
            return new IncorrectCommand("Usage: delete <index>");
        }
        try {
            int index = Integer.parseInt(args) - 1;
            if (index < 0 || index >= TaskList.getSize()) {
                return new IncorrectCommand("Invalid task number: " + (index + 1));
            }
            return new DeleteCommand(index);
        } catch (NumberFormatException e) {
            return new IncorrectCommand("Usage: delete <index> - Index must be a number.");
        }
    }

    private static Command parseMarkCommand(String args) throws SquidException{
        if (args.isEmpty()) {
            return new IncorrectCommand("Usage: mark <index>");
        }
        try {
            int index = Integer.parseInt(args) - 1;
            if (index < 0 || index >= TaskList.getSize()) {
                return new IncorrectCommand("Invalid task number: " + (index + 1));
            }
            return new MarkCommand(index);
        } catch (NumberFormatException e) {
            return new IncorrectCommand("Usage: mark <index> - Index must be a number.");
        }
    }

    private static Command parseUnmarkCommand(String args) throws SquidException{
        if (args.isEmpty()) {
            return new IncorrectCommand("Usage: unmark <index>");
        }
        try {
            int index = Integer.parseInt(args) - 1;
            if (index < 0 || index >= TaskList.getSize()) {
                return new IncorrectCommand("Invalid task number: " + (index + 1));
            }
            return new UnmarkCommand(index);
        } catch (NumberFormatException e) {
            return new IncorrectCommand("Usage: unmark <index> - Index must be a number.");
        }
    }

    private static Command parseTodoCommand(String args) {
        if (args.isEmpty()) {
            return new IncorrectCommand("Usage: todo <description>");
        }
        return new TodoCommand(args);
    }

    private static Command parseDeadlineCommand(String args) throws DateTimeParseException{
        if (!args.contains("/by")) {
            return new IncorrectCommand("Usage: deadline <description> /by <yyyy-mm-dd HHmm>");
        }
        int byIndex = args.indexOf("/by");
        String description = args.substring(0, byIndex).trim();
        String byString = args.substring(byIndex + 4).trim();
        try {
            LocalDateTime by = LocalDateTime.parse(byString, formatter);
            return new DeadlineCommand(description, by);
        } catch (DateTimeParseException e) {
            return new IncorrectCommand("Invalid date and time format. Please use format: yyyy-mm-dd HHmm.");
        }
    }

    private static Command parseEventCommand(String args) throws DateTimeParseException{
        if (!args.contains("/from") || !args.contains("/to")) {
            return new IncorrectCommand("Usage: event <description> /from <yyyy-mm-dd HHmm> /to <yyyy-mm-dd HHmm>");
        }
        int fromIndex = args.indexOf("/from");
        int toIndex = args.indexOf("/to");
        String description = args.substring(0, fromIndex).trim();
        String fromString = args.substring(fromIndex + 6, toIndex).trim();
        String toString = args.substring(toIndex + 4).trim();
        try {
            LocalDateTime from = LocalDateTime.parse(fromString, formatter);
            LocalDateTime to = LocalDateTime.parse(toString, formatter);
            return new EventCommand(description, from, to);
        } catch (DateTimeParseException e) {
            return new IncorrectCommand("Invalid date and time format. Please use format: yyyy-mm-dd HHmm.");
        }
    }

    private static Command parseShowCommand(String args) throws DateTimeParseException {
        try {
            LocalDate date = LocalDate.parse(args, DateTimeFormatter.ISO_LOCAL_DATE);
            return new ShowCommand(date);
        } catch (DateTimeParseException e) {
            return new IncorrectCommand("Invalid date format. Please use YYYY-MM-DD.");
        }
    }
}