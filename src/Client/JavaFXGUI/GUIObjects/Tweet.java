package Client.JavaFXGUI.GUIObjects;

/**
 *
 */
public final class Tweet {
    public String username;
    public String hashtag;
    public String message;
    public String datetime;

    /**
     * @param username
     * @param hashtag
     * @param message
     * @param datetime
     */
    public Tweet(String username, String hashtag, String message, String datetime) {
        this.username = username;
        this.hashtag = hashtag;
        this.message = message;
        this.datetime = datetime;
    }

    /**
     * @return
     */
    @Override
    public String toString() {
        return username + "\n" + hashtag + "\n" + message + "\n" + datetime;
    }
}
