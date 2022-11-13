package Client.ClientClass;

import Client.Misc.ServerRecord;
import Shared.Packet.Packet;
import Shared.Packet.PacketHelper;
import Shared.Packet.Session;

import java.io.IOException;
import java.net.Socket;

public class TwitterClient {
    private static final TwitterClient instance = new TwitterClient();

    private Socket clientSocket;
    private String srv_ip;
    private int srv_port;
    private PacketHelper packetHelper;

    private Session session = new Session();

    TwitterClient() {
        if (instance != null) throw new InstantiationError("Creating this object is not allowed.");
    }

    public static TwitterClient getInstance() {
        return instance;
    }

    public void init(ServerRecord server) {
        this.srv_ip = server.ip();
        this.srv_port = server.port();

        System.out.println("Ip:" + srv_ip);
        System.out.println("Port:" + srv_port);
    }

    public void connect() throws IOException {
        clientSocket = new Socket(srv_ip, srv_port);
        packetHelper = new PacketHelper(clientSocket);

        System.out.println("Connected.");
    }

    public void disconnect() throws IOException {
        clientSocket.close();
        System.out.println("Disconnected.");
    }

    public void sendPacket(Packet to_send) {
        packetHelper.sendPacket(to_send);
    }

    public Packet getPacket() throws IOException {
        return packetHelper.getPacket();
    }

    public PacketHelper getPacketHelper() {
        return this.packetHelper;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public Session getSession() {
        return this.session;
    }
}
