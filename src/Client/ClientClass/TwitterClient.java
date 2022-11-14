package Client.ClientClass;

import Client.Misc.ServerRecord;
import Shared.Packet.Packet;
import Shared.Packet.PacketHelper;
import Shared.Packet.Session;

import java.io.IOException;
import java.net.Socket;

/**
 * A SINGLETON class used for communication with Twitter 2 Server.
 */
public class TwitterClient {
    private static final TwitterClient instance = new TwitterClient();

    private Socket clientSocket;
    private String srv_ip;
    private int srv_port;
    private PacketHelper packetHelper;

    private Session session = new Session();

    /**
     * Singleton constructor for reflection attacks protection.
     */
    TwitterClient() {
        if (instance != null) throw new InstantiationError("Creating this object is not allowed.");
    }

    /**
     *
     * @return a singleton instance of TwitterClient.
     */
    public static TwitterClient getInstance() {
        return instance;
    }

    /**
     * Initializes TwitterClient params to connect to the server.
     * @param server a server record with ip and port.
     */
    public void init(ServerRecord server) {
        this.srv_ip = server.ip();
        this.srv_port = server.port();

        System.out.println("Ip:" + srv_ip);
        System.out.println("Port:" + srv_port);
    }

    /**
     * Connects the client to the server.
     * @throws IOException when there's a problem on the socket.
     */
    public void connect() throws IOException {
        clientSocket = new Socket(srv_ip, srv_port);
        packetHelper = new PacketHelper(clientSocket);

        System.out.println("Connected.");
    }

    /**
     * Disconnects from the server.
     * @throws IOException when there's a problem on the socket.
     */
    public void disconnect() throws IOException {
        clientSocket.close();
        System.out.println("Disconnected.");
    }

    /**
     * Sends a packet through the socket.
     * @param to_send the packet to be sent.
     */
    public void sendPacket(Packet to_send) {
        packetHelper.sendPacket(to_send);
    }

    /**
     * Reads a packet from the socket.
     * @return the packet read from the socket.
     * @throws IOException when there's a problem on the socket.
     */
    public Packet getPacket() throws IOException {
        return packetHelper.getPacket();
    }

    /**
     * Returns the packet helper instantiated for the connection.
     * @return the PacketHelper used in the connection.
     */
    public PacketHelper getPacketHelper() {
        return this.packetHelper;
    }

    /**
     * Sets the current session.
     * @param session a session properly returned by the server.
     */
    public void setSession(Session session) {
        this.session = session;
    }

    /**
     * Returns the current session.
     * @return the session currently in use.
     */
    public Session getSession() {
        return this.session;
    }
}
