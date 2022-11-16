package Server.Operations.OpCommands;

import Server.Queries.QueryAdapter.QueryUpdateAdapter;
import Server.Queries.QueryCommand.MySQLQueryCommand;
import Server.Queries.QueryCommand.UserLogoutQuery;
import Shared.ErrorHandling.ErrorCode;
import Shared.ErrorHandling.Exceptions.SessionException;
import Shared.Packet.Packet;
import Shared.Packet.Session;

import java.net.Socket;
import java.sql.SQLException;

public class UserLogoutOperation extends OperationCommand  {
    private final Packet packet;

    /**
     * Sets both the socket and the packet needed in order to execute the operation.
     * @param socket the socket where packets will be sent or read.
     * @param packet the packet needed for the operation.
     */
    public UserLogoutOperation(Socket socket, Packet packet) {
        super(socket);
        this.packet = packet;
    }

    /**
     * Executes the current command.
     * @return a response Packet.
     * @throws SessionException if the session provided by the client is invalid.
     * @throws SQLException if a query cannot be executed.
     */
    @Override
    public Packet execute() throws SessionException, SQLException {
        sessionIsValid(packet);
        Packet response = packet.clone();

        MySQLQueryCommand logout = new UserLogoutQuery(packet.session.uid, packet.session.session_key);
        QueryUpdateAdapter logoutAdpt = new QueryUpdateAdapter(logout);

        if (logoutAdpt.execute() != 0) {
            response.isSuccessful = true;
            response.session = new Session(null, null, null);
        }
        else {
            response.isSuccessful = false;
            response.errorCode = ErrorCode.LOGOUT_ERROR;
            throw new SessionException("Session not found or expired.");
        }

        return response;
    }
}
