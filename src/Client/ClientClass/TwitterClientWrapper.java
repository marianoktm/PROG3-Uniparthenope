package Client.ClientClass;

import Shared.ErrorHandling.ErrorCode;
import Shared.Packet.Packet;
import Shared.Packet.RequestCode;
import Shared.Packet.Session;

import java.io.IOException;
import java.util.List;

public class TwitterClientWrapper {
    private static final TwitterClient TWITTER_CLIENT_INSTANCE = TwitterClient.getInstance();

    public static void connect() throws IOException {
        TWITTER_CLIENT_INSTANCE.connect();
    }

    public static void disconnect() throws IOException {
        TWITTER_CLIENT_INSTANCE.disconnect();
    }

    public static void sendPacket(Packet to_send) {
        System.out.println("Sending:");
        System.out.println(to_send.toString());
        TWITTER_CLIENT_INSTANCE.getPacketHelper().sendPacket(to_send);
    }

    public static Packet getPacket() throws IOException {
        return TWITTER_CLIENT_INSTANCE.getPacketHelper().getPacket();
    }

    public static void setSession(Session session) {
        TWITTER_CLIENT_INSTANCE.setSession(session);
    }

    public static Session getSession() {
        return TWITTER_CLIENT_INSTANCE.getSession();
    }

    public static Packet generateSessionlessPacket(RequestCode requestCode, List<?> data) {
        return new Packet(requestCode, null, data, null, ErrorCode.NONE);
    }

    public static Packet generatePacket(RequestCode requestCode, List<?> data) {
        return new Packet(requestCode, TWITTER_CLIENT_INSTANCE.getSession(), data, null, ErrorCode.NONE);
    }
}
