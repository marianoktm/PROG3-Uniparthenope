package Shared.ErrorHandling.Exceptions;

public class MissingSessionException extends Exception {
    public MissingSessionException(String errorMessage) {
        super(errorMessage);
    }
}
