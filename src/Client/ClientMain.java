package Client;

import Client.ClientClass.TwitterClient;
import Client.Misc.ClientConfig;
import Shared.ErrorHandling.ErrorCode;
import Shared.Packet.Packet;
import Shared.Packet.Session;

import java.util.ArrayList;
import java.util.List;

public class ClientMain {
    public static void main(String[] args) throws InterruptedException {
        ClientConfig config = new ClientConfig(args);
        TwitterClient client = new TwitterClient(config.getServerRecord());

        client.connect();

        Session dummy_session = new Session(null, "marianoktm", null);
        Packet to_send = new Packet(0, dummy_session, null, null, ErrorCode.NONE);

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
