package Server.Operations;

import Shared.Packet;

import java.net.Socket;

public abstract class OperationCommand {
    protected Socket socket;

    OperationCommand(Socket socket) {
        this.socket = socket;
    }

    public abstract Packet execute();
}
