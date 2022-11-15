package Shared.ErrorHandling.Exceptions;

/**
 *
 */
public class SessionException extends Exception {
    /**
     * @param errorMessage
     */
    public SessionException(String errorMessage) {
        super(errorMessage);
    }
}
