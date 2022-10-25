package Client;

import Client.ClientClass.TwitterClient;
import Client.Misc.ClientConfig;
import Shared.Packet;
import Shared.Session;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ClientMain {
    public static void main(String[] args) throws InterruptedException {
        ClientConfig config = new ClientConfig(args);
        TwitterClient client = new TwitterClient(config.getServerRecord());

        client.connect();

        Session dummy_session = new Session(null, "marianoktm");
        Packet to_send = new Packet(0, dummy_session, null, null);

        client.sendPacket(to_send);

        Packet packet = client.getPacket();

        List<?> list = new ArrayList<>();

        list = packet.data;

        for (Object x : list) {
            System.out.println(x);
        }

        client.disconnect();
    }
}
