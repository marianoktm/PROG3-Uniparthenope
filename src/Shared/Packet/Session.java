package Shared.Packet;

public class Session {
    public String session_key;
    public String username;
    public String uid;

    public Session() {
        this.session_key = this.username = this.uid = null;
    }

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

    @Override
    public String toString() {
        return "UID: " + this.uid + " --- username: " + this.username + " --- session_key: " + this.session_key;
    }
}

