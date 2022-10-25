package Shared;

import Client.Misc.ClientConfig;
import com.google.gson.Gson;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
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

    public Packet getPacket() {
        String JSONToGet = null;

        try {
            DataInputStream dataIn = new DataInputStream(clientSocket.getInputStream());
            JSONToGet = dataIn.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        Gson gson = new Gson();
        return gson.fromJson(JSONToGet, Packet.class);
    }
}
