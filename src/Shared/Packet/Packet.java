package Shared.Packet;

import Shared.ErrorHandling.ErrorCode;

import java.util.ArrayList;
import java.util.List;

public class Packet implements Cloneable {
    public int request;
    public Session session;
    public List<?> data;
    public Boolean isSuccessful;
    public ErrorCode errorCode;

    public Packet(int request, Session session, List<?> data, Boolean isSuccessful, ErrorCode errorCode) {
        this.request = request;
        this.session = session;
        this.data = data;
        this.isSuccessful = isSuccessful;
        this.errorCode = errorCode;
    }

    @Override
    public Packet clone() {
        try {
            Packet clone = (Packet) super.clone();

            clone.request = this.request;
            clone.session = new Session(session.session_key, session.username, session.uid);
            clone.data = new ArrayList<>(this.data);
            clone.isSuccessful = this.isSuccessful;
            clone.errorCode = this.errorCode;

            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}