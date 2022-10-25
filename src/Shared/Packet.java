package Shared;

import java.util.ArrayList;
import java.util.List;

public class Packet implements Cloneable {
    public int request;
    public Session session;
    public List<?> data;
    public Boolean bool;

    public Packet(int request, Session session, List<?> data, Boolean bool) {
        this.request = request;
        this.session = session;
        this.data = data;
        this.bool = bool;
    }

    @Override
    public Packet clone() {
        try {
            Packet clone = (Packet) super.clone();

            clone.request = this.request;
            clone.session = new Session(session.session_key, session.user);
            clone.data = new ArrayList<>(this.data);
            clone.bool = this.bool;

            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}