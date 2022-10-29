package Shared.ErrorHandling.Exceptions;

public class InvalidSessionException extends SessionException {
    public InvalidSessionException(String errorMessage) {
        super(errorMessage);
    }
}
