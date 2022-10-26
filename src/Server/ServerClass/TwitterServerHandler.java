package Server.ServerClass;

import Server.Operations.Op0Register;
import Server.Operations.OperationCommand;
import Shared.ErrorHandling.ErrorCode;
import Shared.ErrorHandling.Exceptions.InvalidSessionException;
import Shared.Packet.Packet;
import Shared.Packet.PacketHelper;
import Shared.Packet.Session;

import java.net.Socket;

class TwitterServerHandler extends Thread {
    private final Socket clientSocket;
    private PacketHelper packetHelper;

    public TwitterServerHandler(Socket socket) {
            this.clientSocket = socket;
            packetHelper = new PacketHelper(socket);
    }

    public void run() {
        Packet packet;
        System.out.println("Connection by client: " + clientSocket.getInetAddress());

        packet = getPacket();
        operation(packet);
    }

    private void sendPacket(Packet to_send) {
        packetHelper.sendPacket(to_send);
    }

    private Packet getPacket() {
        return packetHelper.getPacket();
    }

    private void operation(Packet packet) {
        OperationCommand op;
        Packet response = packet.clone();

        try {
            switch (packet.request) {
                case 0: // register
                    op = new Op0Register(clientSocket, packet);
                    response = op.execute();
            }
        }
        catch (InvalidSessionException e) {
            e.printStackTrace();
            response.isSuccessful = false;
            response.errorCode = ErrorCode.SESSION_NOT_FOUND;
        }
        finally {
            sendPacket(response);
        }
    }
}


