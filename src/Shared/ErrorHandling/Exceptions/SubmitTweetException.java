package Shared.ErrorHandling.Exceptions;

public class SubmitTweetException extends Exception {
    public SubmitTweetException(String errorMessage) {
        super(errorMessage);
    }
}
