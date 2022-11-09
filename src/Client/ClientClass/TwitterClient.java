package Client.ClientClass;

import Client.Misc.ServerRecord;
import Shared.Packet.Packet;
import Shared.Packet.PacketHelper;

import java.io.IOException;
import java.net.Socket;

public class TwitterClient {
    private Socket clientSocket;
    String srv_ip;
    int srv_port;
    PacketHelper packetHelper;

    public TwitterClient(ServerRecord server) {
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
}
