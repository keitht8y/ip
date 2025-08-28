package Monarch.Exceptions;

public class MonException extends RuntimeException{
    public MonException(String errorMessage) {
        super(errorMessage);
    }
}
