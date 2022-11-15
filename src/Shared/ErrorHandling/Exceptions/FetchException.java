package Shared.ErrorHandling.Exceptions;

/**
 *
 */
public class FetchException extends Exception {
    /**
     * @param errorMessage
     */
    public FetchException(String errorMessage) {
        super(errorMessage);
    }
}
