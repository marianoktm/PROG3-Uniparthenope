package Client.JavaFXGUI.Controllers;

import Client.ClientClass.TwitterClientWrapper;
import Shared.Packet.Packet;
import Shared.Packet.RequestCode;

import java.io.IOException;
import java.util.List;

/**
 * A class for GUI Controllers that need to connect to a server. Provides useful methods for sending and receiving packets.
 */
public class ConnectedUIController implements Controller {
    /**
     * Connect to the server and sends a packet.
     * @param packet the packet that will be sent
     * @throws IOException if the packet can't be sent.
     */
    protected void connectAndSend(Packet packet) throws IOException {
        TwitterClientWrapper.connect();
        TwitterClientWrapper.sendPacket(packet);
    }

    /**
     * Reads a packet and disconnects the client from the server.
     * @return the packet read.
     * @throws IOException if a packet can't be read.
     */
    protected Packet getAndDisconnect() throws IOException {
        Packet out = TwitterClientWrapper.getPacket();
        TwitterClientWrapper.disconnect();
        return out;
    }

    /**
     * Send a sessioned packet with the given request and data to be processed. The session is stored and fetched from TwitterClient class.
     * @param requestCode the request made to the server.
     * @param data the data to be processed by the server.
     * @throws IOException if a packet can't be sent.
     */
    protected void sendSessionedPacket(RequestCode requestCode, List<?> data) throws IOException {
        connectAndSend(TwitterClientWrapper.generatePacket(requestCode, data));
    }

    /**
     * Send a sessionless packet with the given request and data to be processed. A new Session object with null values is instantiated.
     * @param requestCode the request made to the server.
     * @param data the data to be processed by the server.
     * @throws IOException if a packet can't be sent.
     */
    protected void sendSessionlessPacket(RequestCode requestCode, List<?> data) throws IOException {
        connectAndSend(TwitterClientWrapper.generateSessionlessPacket(requestCode, data));
    }

}
