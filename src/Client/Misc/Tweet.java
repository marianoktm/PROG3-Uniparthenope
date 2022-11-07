package Client.Misc;

public final class Tweet {
    public String username;
    public String hashtag;
    public String message;
    public String datetime;

    public Tweet(String username, String hashtag, String message, String datetime) {
        this.username = username;
        this.hashtag = hashtag;
        this.message = message;
        this.datetime = datetime;
    }
}
