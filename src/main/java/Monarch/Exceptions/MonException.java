package Monarch.Exceptions;

/**
 * Represents an error that occurs in the chat bot.
 */
public class MonException extends RuntimeException{
    /**
     * Constructor for the error.
     *
     * @param errorMessage Error message.
     */
    public MonException(String errorMessage) {
        super(errorMessage);
    }
}
