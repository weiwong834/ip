package exceptions;

/**
 * Exception thrown when an attempt is made to unmark a task that is already unmarked.
 * This helps in preventing operations on tasks that are in an invalid state for the requested operation.
 */
public class AlreadyUnmarkedException extends Exception {
    /**
     * Constructs an AlreadyUnmarkedException with the specified detail message.
     *
     * @param message the detail message.
     */
    public AlreadyUnmarkedException(String message) {
        super(message);
    }
}
