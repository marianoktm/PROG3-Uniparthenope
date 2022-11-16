package Shared.Packet;

/**
 * A class that provides behavior for the Session.
 */
public class Session {
    public String session_key;
    public String username;
    public String uid;

    /**
     * Creates an unregistered session.
     */
    public Session() {
        this.session_key = this.username = this.uid = null;
    }

    /**
     * Sets session fields.
     * @param session_key the session unique identifier.
     * @param username the user username. Null if an admin.
     * @param uid the user or admin id.
     */
    public Session(String session_key, String username, String uid) {
        this.session_key = session_key;
        this.username = username;
        this.uid = uid;
    }

    /**
     * Sets session fields using another session object.
     * @param session the session that will be copied.
     */
    public Session(Session session) {
        this.session_key = session.session_key;
        this.username = session.username;
        this.uid = session.uid;
    }

    /**
     * Converts the class in a String.
     * @return the class as a String.
     */
    @Override
    public String toString() {
        return "UID: " + this.uid + " --- username: " + this.username + " --- session_key: " + this.session_key;
    }
}

