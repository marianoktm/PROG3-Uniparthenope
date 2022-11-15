package Shared.ErrorHandling.Exceptions;

/**
 *
 */
public class InvalidSessionException extends SessionException {
    /**
     * @param errorMessage
     */
    public InvalidSessionException(String errorMessage) {
        super(errorMessage);
    }
}
