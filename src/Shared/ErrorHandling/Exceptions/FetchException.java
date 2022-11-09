package Shared.ErrorHandling.Exceptions;

public class FetchException extends Exception {
    public FetchException(String errorMessage) {
        super(errorMessage);
    }
}
