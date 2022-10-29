package Shared.Packet;

public class Session {
    public String session_key;
    public String username;
    public String uid;

    public Session(String session_key, String username, String uid) {
        this.session_key = session_key;
        this.username = username;
        this.uid = uid;
    }

    public Session(Session session) {
        this.session_key = session.session_key;
        this.username = session.username;
        this.uid = session.uid;
    }
}

