package Shared.ErrorHandling.Exceptions;

public class InvalidTwitterOpException extends Exception{
    public InvalidTwitterOpException(String errorMessage) {
        super(errorMessage);
    }
}
