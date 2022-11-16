package Server.ServerClass;

import Server.Operations.OperationFacade;
import Shared.ErrorHandling.ErrorCode;
import Shared.ErrorHandling.Exceptions.BanException;
import Shared.ErrorHandling.Exceptions.InvalidTwitterOpException;
import Shared.ErrorHandling.Exceptions.SessionException;
import Shared.Packet.Packet;
import Shared.Packet.PacketHelper;

import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;

/**
 * A class that handles single thread of Twitter 2 Server.
 */
class TwitterServerHandler extends Thread {
    private final Socket clientSocket;
    private final PacketHelper packetHelper;

    /**
     * Sets the socket that will be used by this handler.
     * @param socket the socket that will be used for communication.
     */
    public TwitterServerHandler(Socket socket) {
            this.clientSocket = socket;
            packetHelper = new PacketHelper(socket);
    }

    /**
     * Actually runs the thread. If a packet is read it executes the corresponding operation.
     */
    public void run() {
        System.out.println("Connection by client: " + clientSocket.getInetAddress());

        boolean didPacketRead;
        do didPacketRead = operation(); while (didPacketRead);

        System.out.println("Connection closed by client: " + clientSocket.getInetAddress());

    }

    /**
     * Sends a packet through the socket.
     * @param to_send the packet that will be sent.
     */
    private void sendPacket(Packet to_send) {
        packetHelper.sendPacket(to_send);
    }

    /**
     * Read a packet from the socket
     * @return the packet read
     * @throws IOException if the socket can't be read
     */
    private Packet getPacket() throws IOException {
        return packetHelper.getPacket();
    }

    /**
     * Executes an operation for the client if a packet can be read from the socket.
     * @return true if a packet is read, false otherwise.
     */
    private boolean operation() {
        Packet packet;
        Packet response;

        try {
            packet = getPacket();
            response = packet.clone();
        } catch (IOException e) {
            return false;
        }

        // Operation handling
        OperationFacade chain = OperationFacade.getInstance();
        try {
            response = chain.fulfillRequest(clientSocket, packet);
        }
        catch (BanException e) {
            e.printStackTrace();
            response.isSuccessful = false;
            response.errorCode = ErrorCode.BANNED;
        }
        catch (SessionException e) {
            e.printStackTrace();
            response.isSuccessful = false;
            response.errorCode = ErrorCode.SESSION_ERROR;
        }
        catch (SQLException e) {
            e.printStackTrace();
            response.isSuccessful = false;
            response.errorCode = ErrorCode.DB_SQL_ERROR;
        }
        catch (InvalidTwitterOpException e) {
            e.printStackTrace();
            response.isSuccessful = false;
            response.errorCode = ErrorCode.INVALID_REQUEST;
        }
        catch (Exception e) {
            e.printStackTrace();
            response.isSuccessful = false;
            response.errorCode = ErrorCode.GENERIC_ERROR;
        }
        finally {
            sendPacket(response);
        }
        return true;
    }
}


