package Shared.ErrorHandling.Exceptions;

public class InvalidSessionException extends Exception {
    public InvalidSessionException(String errorMessage) {
        super(errorMessage);
    }
}
