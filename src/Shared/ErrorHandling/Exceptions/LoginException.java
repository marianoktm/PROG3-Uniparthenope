package Shared.ErrorHandling.Exceptions;

public class LoginException extends Exception {
    public LoginException(String errorMessage) {
        super(errorMessage);
    }
}