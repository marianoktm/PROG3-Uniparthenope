package Shared;

public class Session {
    public String session_key;
    public String user;

    public Session(String session_key, String user) {
        this.session_key = session_key;
        this.user = user;
    }
}

