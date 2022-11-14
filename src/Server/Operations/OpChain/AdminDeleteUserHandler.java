package Server.Operations.OpChain;

import Server.Operations.OpCommands.AdminDeleteUserOperation;
import Shared.ErrorHandling.Exceptions.BanException;
import Shared.ErrorHandling.Exceptions.InvalidTwitterOpException;
import Shared.ErrorHandling.Exceptions.SessionException;
import Shared.Packet.Packet;
import Shared.Packet.RequestCode;

import java.net.Socket;
import java.sql.SQLException;

public class AdminDeleteUserHandler extends OperationChain {
    private final RequestCode requestCode = RequestCode.DELETE_USER;

    @Override
    public Packet perform(Socket socket, Packet packet) throws SessionException, SQLException, InvalidTwitterOpException, BanException {
        if (canHandle(packet, requestCode))
            return (new AdminDeleteUserOperation(socket, packet)).execute();
        else if (next != null)
            return next.perform(socket, packet);
        else
            throw new InvalidTwitterOpException("Invalid requestCode provided by client");
    }
}
