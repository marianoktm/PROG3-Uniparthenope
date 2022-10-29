package Server.ServerClass;

import Server.Operations.OperationFacade;
import Shared.ErrorHandling.ErrorCode;
import Shared.ErrorHandling.Exceptions.InvalidTwitterOpException;
import Shared.ErrorHandling.Exceptions.SessionException;
import Shared.Packet.Packet;
import Shared.Packet.PacketHelper;

import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;

class TwitterServerHandler extends Thread {
    private final Socket clientSocket;
    private final PacketHelper packetHelper;

    public TwitterServerHandler(Socket socket) {
            this.clientSocket = socket;
            packetHelper = new PacketHelper(socket);
    }

    public void run() {
        System.out.println("Connection by client: " + clientSocket.getInetAddress());

        boolean didPacketRead;
        do didPacketRead = operation(); while (didPacketRead);

        System.out.println("Connection closed by client: " + clientSocket.getInetAddress());

    }

    private void sendPacket(Packet to_send) {
        packetHelper.sendPacket(to_send);
    }

    private Packet getPacket() throws IOException {
        return packetHelper.getPacket();
    }

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


