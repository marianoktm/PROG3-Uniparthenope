package Server.Operations.OpChain;


import Server.Operations.OpCommands.UserFetchFollowedTweetsOperation;
import Shared.ErrorHandling.Exceptions.BanException;
import Shared.ErrorHandling.Exceptions.InvalidTwitterOpException;
import Shared.ErrorHandling.Exceptions.SessionException;
import Shared.Packet.Packet;
import Shared.Packet.RequestCode;

import java.net.Socket;
import java.sql.SQLException;

/**
 * A CHAIN OF RESPONSIBILITY handler to handle FETCH_TWEETS request.
 */
public class FetchTweetHandler extends OperationChain {
    private final RequestCode requestCode = RequestCode.FETCH_TWEETS;

    /**
     * {@inheritDoc}
     * @param socket the socket where the packet will be sent and read.
     * @param packet the packet that will be sent or read.
     * @return a response packet
     * @throws SessionException if the session provided by the client is invalid.
     * @throws SQLException if a query can't be executed.
     * @throws InvalidTwitterOpException if the request provided by the client is invalid.
     * @throws BanException if a user tries to log in while banned.
     */
    @Override
    public Packet perform(Socket socket, Packet packet) throws SessionException, SQLException, InvalidTwitterOpException, BanException {
        if (canHandle(packet, requestCode))
            return (new UserFetchFollowedTweetsOperation(socket, packet)).execute();
        else if (next != null)
            return next.perform(socket, packet);
        else
            throw new InvalidTwitterOpException("Invalid requestCode provided by client");
    }
}
