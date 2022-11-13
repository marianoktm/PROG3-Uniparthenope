package Client.JavaFXGUI.Controllers;

import Client.ClientClass.TwitterClientWrapper;
import Shared.Packet.Packet;
import Shared.Packet.RequestCode;

import java.io.IOException;
import java.util.List;

public class ConnectedUIController implements Controller {
    protected void connectAndSend(Packet packet) throws IOException {
        TwitterClientWrapper.connect();
        TwitterClientWrapper.sendPacket(packet);
    }

    protected Packet getAndDisconnect() throws IOException {
        Packet out = TwitterClientWrapper.getPacket();
        TwitterClientWrapper.disconnect();
        return out;
    }

    protected void sendSessionedPacket(RequestCode requestCode, List<?> data) throws IOException {
        connectAndSend(TwitterClientWrapper.generatePacket(requestCode, data));
    }

    protected void sendSessionlessPacket(RequestCode requestCode, List<?> data) throws IOException {
        connectAndSend(TwitterClientWrapper.generateSessionlessPacket(requestCode, data));
    }

}
