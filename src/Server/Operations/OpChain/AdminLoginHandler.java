package Server.Operations.OpChain;

import Server.Operations.OpCommands.AdminLoginOperation;
import Shared.ErrorHandling.Exceptions.InvalidTwitterOpException;
import Shared.ErrorHandling.Exceptions.SessionException;
import Shared.Packet.Packet;
import Shared.Packet.RequestCode;

import java.net.Socket;
import java.sql.SQLException;

public class AdminLoginHandler extends OperationChain {
    private final RequestCode requestCode = RequestCode.ADMIN_LOGIN;

    @Override
    public Packet perform(Socket socket, Packet packet) throws SessionException, SQLException, InvalidTwitterOpException {
        if (canHandle(packet, requestCode))
            return (new AdminLoginOperation(socket, packet)).execute();
        else if (next != null)
            return next.perform(socket, packet);
        else
            throw new InvalidTwitterOpException("Invalid requestCode provided by client");
    }
}
