package Server.Operations.OpChain;

import Server.Operations.OpCommands.UserFollowOperation;
import Shared.ErrorHandling.Exceptions.InvalidTwitterOpException;
import Shared.ErrorHandling.Exceptions.SessionException;
import Shared.Packet.Packet;
import Shared.Packet.RequestCode;

import java.net.Socket;
import java.sql.SQLException;

public class UserFollowHandler extends OperationChain {
    private final RequestCode requestCode = RequestCode.USER_FOLLOW;

    @Override
    public Packet perform(Socket socket, Packet packet) throws SessionException, SQLException, InvalidTwitterOpException {
        if (canHandle(packet, requestCode))
            return (new UserFollowOperation(socket, packet)).execute();
        else if (next != null)
            return next.perform(socket, packet);
        else
            throw new InvalidTwitterOpException("Invalid requestCode provided by client");
    }
}
