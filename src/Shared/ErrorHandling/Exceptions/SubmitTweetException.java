package Shared.ErrorHandling.Exceptions;

/**
 *
 */
public class SubmitTweetException extends Exception {
    /**
     * @param errorMessage
     */
    public SubmitTweetException(String errorMessage) {
        super(errorMessage);
    }
}
