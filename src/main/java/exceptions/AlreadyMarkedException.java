package exceptions;

/**
 * Exception thrown when an attempt is made to mark a task that is already marked.
 * This helps in preventing operations on tasks that are in an invalid state for the requested operation.
 */
public class AlreadyMarkedException extends Exception {
    /**
     * Constructs an AlreadyMarkedException with the specified detail message.
     *
     * @param message the detail message.
     */
    public AlreadyMarkedException(String message) {
        super(message);
    }
}
