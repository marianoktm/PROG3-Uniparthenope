package Shared.ErrorHandling.Exceptions;

/**
 *
 */
public class EmptyFieldException extends Exception {
    /**
     * @param errorMessage
     */
    public EmptyFieldException(String errorMessage) {
        super(errorMessage);
    }
}
