package Shared.Packet;

import com.google.gson.Gson;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * Utils class that provides useful functions to handle packet.
 */
public class PacketHelper {
    private final Socket clientSocket;

    /**
     * Sets the socket that will be used for communication.
     * @param socket the socket that will be used.
     */
    public PacketHelper(Socket socket) {
        this.clientSocket = socket;
    }

    /**
     * Sends a packet through the socket
     * @param to_send the packet that will be sent.
     */
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

    /**
     * Reads a packet from the socket.
     * @return the packet read.
     * @throws IOException if a packet can't be read.
     */
    public Packet getPacket() throws IOException {
        String jsonToGet;

        InputStream inputStream = clientSocket.getInputStream();
        DataInputStream dataIn = new DataInputStream(inputStream);
        jsonToGet = dataIn.readUTF();

        Gson gson = new Gson();
        return gson.fromJson(jsonToGet, Packet.class);
    }
}
