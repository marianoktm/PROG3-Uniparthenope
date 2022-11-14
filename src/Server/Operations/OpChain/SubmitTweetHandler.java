package Server.Operations.OpChain;

import Server.Operations.OpCommands.SubmitTweetOperation;
import Shared.ErrorHandling.Exceptions.BanException;
import Shared.ErrorHandling.Exceptions.InvalidTwitterOpException;
import Shared.ErrorHandling.Exceptions.SessionException;
import Shared.Packet.Packet;
import Shared.Packet.RequestCode;

import java.net.Socket;
import java.sql.SQLException;

public class SubmitTweetHandler extends OperationChain {
    private final RequestCode requestCode = RequestCode.SUBMIT_TWEET;

    @Override
    public Packet perform(Socket socket, Packet packet) throws SessionException, SQLException, InvalidTwitterOpException, BanException {
        if (canHandle(packet, requestCode))
            return (new SubmitTweetOperation(socket, packet)).execute();
        else if (next != null)
            return next.perform(socket, packet);
        else
            throw new InvalidTwitterOpException("Invalid requestCode provided by client");
    }
}
