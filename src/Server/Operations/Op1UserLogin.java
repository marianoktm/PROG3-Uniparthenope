package Server.Operations;

import Shared.Packet.Packet;

import java.net.Socket;

public class Op1UserLogin extends OperationCommand {
    private Packet packet;

    public Op1UserLogin(Socket socket, Packet packet) {
        super(socket);
        this.packet = packet;
    }

    @Override
    public Packet execute() {
        return null;
    }
}
