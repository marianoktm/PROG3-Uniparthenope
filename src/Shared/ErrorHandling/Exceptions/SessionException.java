package Shared.ErrorHandling.Exceptions;

public class SessionException extends Exception {
    public SessionException(String errorMessage) {
        super(errorMessage);
    }
}
