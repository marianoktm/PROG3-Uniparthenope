package Server.Operations.OpChain;

import Shared.ErrorHandling.Exceptions.BanException;
import Shared.ErrorHandling.Exceptions.InvalidTwitterOpException;
import Shared.ErrorHandling.Exceptions.SessionException;
import Shared.Packet.Packet;
import Shared.Packet.RequestCode;

import java.net.Socket;
import java.sql.SQLException;

/**
 *
 */
public abstract class OperationChain {
    protected OperationChain next;

    /**
     * @param next
     */
    public void setNextChain(OperationChain next) {
        this.next = next;
    }

    /**
     * @param socket
     * @param packet
     * @return
     * @throws SessionException
     * @throws SQLException
     * @throws InvalidTwitterOpException
     * @throws BanException
     */
    public abstract Packet perform(Socket socket, Packet packet) throws SessionException, SQLException, InvalidTwitterOpException, BanException;

    /**
     * @param packet
     * @param requestCode
     * @return
     */
    protected boolean canHandle(Packet packet, RequestCode requestCode) {
        return packet.request == requestCode;
    }
}
