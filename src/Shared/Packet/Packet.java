package Shared.Packet;

import Shared.ErrorHandling.ErrorCode;

import java.util.ArrayList;
import java.util.List;

public class Packet implements Cloneable {
    public RequestCode request;
    public Session session;
    public List<?> data;
    public Boolean isSuccessful;
    public ErrorCode errorCode;

    public Packet(RequestCode request, Session session, List<?> data, Boolean isSuccessful, ErrorCode errorCode) {
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

            if (this.session != null) clone.session = new Session(session.session_key, session.username, session.uid);
            else clone.session = null;

            if (this.data != null) clone.data = new ArrayList<>(this.data);
            else clone.data = null;

            clone.isSuccessful = this.isSuccessful;
            clone.errorCode = this.errorCode;

            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}