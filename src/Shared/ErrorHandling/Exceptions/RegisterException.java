package Shared.ErrorHandling.Exceptions;

public class RegisterException extends Exception {
    public RegisterException(String errorMessage) {
        super(errorMessage);
    }
}
