package Client.JavaFXGUI.GUIObjects;

/**
 * Tweet class for holding Tweet information. It acts as a record, so it does not have setters and getters.
 */
public final class Tweet {
    public String username;
    public String hashtag;
    public String message;
    public String datetime;

    /**
     * Constructor for Tweet class. It sets username, hashtag, message and date of the tweet.
     * @param username the tweet sender's username
     * @param hashtag the tweet's hashtag
     * @param message the actual tweet
     * @param datetime tweet post date
     */
    public Tweet(String username, String hashtag, String message, String datetime) {
        this.username = username;
        this.hashtag = hashtag;
        this.message = message;
        this.datetime = datetime;
    }

    /**
     * Converts the class to a String.
     * @return the class as a String.
     */
    @Override
    public String toString() {
        return username + "\n" + hashtag + "\n" + message + "\n" + datetime;
    }
}
