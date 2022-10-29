package Shared.Packet;

import com.google.gson.Gson;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class PacketHelper {
    private final Socket clientSocket;

    public PacketHelper(Socket socket) {
        this.clientSocket = socket;
    }

    public void sendPacket(Packet to_send) {
        Gson gson = new Gson();
        String JSONToSend = gson.toJson(to_send);

        try {
            DataOutputStream dataOut = new DataOutputStream(clientSocket.getOutputStream());
            dataOut.writeUTF(JSONToSend);
            dataOut.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Packet getPacket() throws IOException {
        String jsonToGet;

        InputStream inputStream = clientSocket.getInputStream();
        DataInputStream dataIn = new DataInputStream(inputStream);
        jsonToGet = dataIn.readUTF();

        Gson gson = new Gson();
        return gson.fromJson(jsonToGet, Packet.class);
    }
}
