package Shared.ErrorHandling.Exceptions;

/**
 *
 */
public class LoginException extends Exception {
    /**
     * @param errorMessage
     */
    public LoginException(String errorMessage) {
        super(errorMessage);
    }
}