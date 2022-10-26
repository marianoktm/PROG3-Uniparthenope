package Shared.ErrorHandling;

public enum ErrorCode {
    NONE,
    GENERIC_ERROR,
    DB_NOT_FOUND,
    SESSION_NOT_FOUND,
    REGISTER_USER_EXISTS,
    LOGIN_NO_USER,
    LOGIN_WRONG_PASSWORD
}
