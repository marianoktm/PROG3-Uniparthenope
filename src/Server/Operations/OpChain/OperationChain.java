package Server.Operations.OpChain;

import Shared.ErrorHandling.Exceptions.BanException;
import Shared.ErrorHandling.Exceptions.InvalidTwitterOpException;
import Shared.ErrorHandling.Exceptions.SessionException;
import Shared.Packet.Packet;
import Shared.Packet.RequestCode;

import java.net.Socket;
import java.sql.SQLException;

/**
 * A CHAIN OF RESPONSIBILITY abstract class that provides signatures for mandatory methods of COR and boilerplate code.
 */
public abstract class OperationChain {
    protected OperationChain next;

    /**
     * Set the next chain handler.
     * @param next the next handler in the chain.
     */
    public void setNextChain(OperationChain next) {
        this.next = next;
    }

    /**
     * Performs the operation if the handler is designed to handle client request, else calls the next handler or throws an exception if it's the latest.
     * @param socket the socket where the packet will be sent and read.
     * @param packet the packet that will be sent or read.
     * @return a response packet
     * @throws SessionException if the session provided by the client is invalid.
     * @throws SQLException if a query can't be executed.
     * @throws InvalidTwitterOpException if the request provided by the client is invalid.
     * @throws BanException if a user tries to log in while banned.
     */
    public abstract Packet perform(Socket socket, Packet packet) throws SessionException, SQLException, InvalidTwitterOpException, BanException;

    /**
     * Checks whether the operation can be handled or not by the current handler.
     * @param packet the packet containing the request code.
     * @param requestCode the current handler request code
     * @return true if can handle, false if not.
     */
    protected boolean canHandle(Packet packet, RequestCode requestCode) {
        return packet.request == requestCode;
    }
}
