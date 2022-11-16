package Shared.Packet;

import Shared.ErrorHandling.ErrorCode;

import java.util.ArrayList;
import java.util.List;

/**
 * Packet class that provides packet behavior.
 */
public class Packet implements Cloneable {
    public RequestCode request;
    public Session session;
    public List<?> data;
    public Boolean isSuccessful;
    public ErrorCode errorCode;

    /**
     * Sets all the packet fields.
     * @param request the request that will be executed.
     * @param session the session that will be used.
     * @param data the data that will be processed.
     * @param isSuccessful null if is a sent packet, true if a request is performed on it, false if a request fails.
     * @param errorCode NONE if is a sent packet, otherwise it tells why the request handling failed.
     */
    public Packet(RequestCode request, Session session, List<?> data, Boolean isSuccessful, ErrorCode errorCode) {
        this.request = request;
        this.session = session;
        this.data = data;
        this.isSuccessful = isSuccessful;
        this.errorCode = errorCode;
    }

    /**
     * Clones the packet.
     * @return the cloned packet.
     */
    @Override
    public Packet clone() {
        try {
            Packet clone = (Packet) super.clone();

            clone.request = this.request;

            if (this.session != null) clone.session = new Session(session.session_key, session.username, session.uid);
            else clone.session = new Session(null, null, null);

            if (this.data != null) clone.data = new ArrayList<>(this.data);
            else clone.data = null;

            clone.isSuccessful = this.isSuccessful;
            clone.errorCode = this.errorCode;

            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    /**
     * Converts the class to a String.
     * @return the class as a String.
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("Request:\n");
        stringBuilder.append(this.request);

        stringBuilder.append("\nSession:\n");
        if (this.session == null) stringBuilder.append("null");
        else stringBuilder.append(this.session);

        stringBuilder.append("\nData:\n");
        if (this.data == null) stringBuilder.append("null");
        else stringBuilder.append(this.data);


        stringBuilder.append("\nisSuccessful:\n");
        if (this.isSuccessful == null) stringBuilder.append("null");
        else stringBuilder.append(isSuccessful);

        stringBuilder.append("\nerrorCode:\n");
        stringBuilder.append(errorCode);

        return stringBuilder.toString();
    }
}