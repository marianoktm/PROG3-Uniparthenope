package Client.ClientClass;

import Shared.ErrorHandling.ErrorCode;
import Shared.Packet.Packet;
import Shared.Packet.RequestCode;
import Shared.Packet.Session;

import java.io.IOException;
import java.util.List;

/**
 * A WRAPPER class used to reduce getInstance() calls to SINGLETON class TwitterClient.
 */
public class TwitterClientWrapper {
    private static final TwitterClient TWITTER_CLIENT_INSTANCE = TwitterClient.getInstance();

    /**
     * Connects the client to the server.
     * @throws IOException when there's a problem on the socket.
     */
    public static void connect() throws IOException {
        TWITTER_CLIENT_INSTANCE.connect();
    }

    /**
     * Disconnects from the server.
     * @throws IOException when there's a problem on the socket.
     */
    public static void disconnect() throws IOException {
        TWITTER_CLIENT_INSTANCE.disconnect();
    }

    /**
     * Sends a packet through the socket.
     * @param to_send the packet to be sent.
     */
    public static void sendPacket(Packet to_send) {
        System.out.println("Sending:");
        System.out.println(to_send.toString());
        TWITTER_CLIENT_INSTANCE.getPacketHelper().sendPacket(to_send);
    }

    /**
     * Reads a packet from the socket.
     * @return the packet read from the socket.
     * @throws IOException when there's a problem on the socket.
     */
    public static Packet getPacket() throws IOException {
        return TWITTER_CLIENT_INSTANCE.getPacketHelper().getPacket();
    }

    /**
     * Sets the current session.
     * @param session a session properly returned by the server.
     */
    public static void setSession(Session session) {
        TWITTER_CLIENT_INSTANCE.setSession(session);
    }

    /**
     * Returns the current session.
     * @return the session currently in use.
     */
    public static Session getSession() {
        return TWITTER_CLIENT_INSTANCE.getSession();
    }

    /**
     * Generates a sessionless packet (A packet with a non-recorded session). Used for login and register operations.
     * @param requestCode the operation to be performed.
     * @param data the data provided to the server.
     * @return a sessionless packet that can be sent to the server.
     */
    public static Packet generateSessionlessPacket(RequestCode requestCode, List<?> data) {
        return new Packet(requestCode, null, data, null, ErrorCode.NONE);
    }

    /**
     * Generates a sessioned packed with the current session. If the current session is non-recorded, the packet will be sessionless.
     * @param requestCode the operation to be performed
     * @param data the data provided to the server
     * @return a sessioned packet or a sessionless packet if the current session is not-recorded.
     */
    public static Packet generatePacket(RequestCode requestCode, List<?> data) {
        return new Packet(requestCode, TWITTER_CLIENT_INSTANCE.getSession(), data, null, ErrorCode.NONE);
    }
}
