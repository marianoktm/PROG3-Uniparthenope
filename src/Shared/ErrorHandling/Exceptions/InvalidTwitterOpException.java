package Shared.ErrorHandling.Exceptions;

/**
 *
 */
public class InvalidTwitterOpException extends Exception{
    /**
     * @param errorMessage
     */
    public InvalidTwitterOpException(String errorMessage) {
        super(errorMessage);
    }
}
