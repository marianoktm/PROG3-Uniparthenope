package Shared.Packet;

/**
 * Enum that provides request codes to operation that will be performed on the server.
 */
public enum RequestCode {
    USER_REGISTER,
    USER_LOGIN,
    USER_LOGOUT,
    USER_FOLLOW,
    SUBMIT_TWEET,
    FETCH_TWEETS,
    FETCH_HASHTAG,
    ADMIN_LOGIN,
    DELETE_USER,
    ADMIN_LOGOUT
}
