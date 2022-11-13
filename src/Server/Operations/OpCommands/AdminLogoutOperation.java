package Server.Operations.OpCommands;

import Server.Queries.QueryAdapter.QueryUpdateAdapter;
import Server.Queries.QueryCommand.AdminLogoutQuery;
import Server.Queries.QueryCommand.MySQLQueryCommand;
import Shared.ErrorHandling.ErrorCode;
import Shared.ErrorHandling.Exceptions.SessionException;
import Shared.Packet.Packet;
import Shared.Packet.Session;

import java.net.Socket;
import java.sql.SQLException;

public class AdminLogoutOperation  extends OperationCommand {
    private final Packet packet;

    public AdminLogoutOperation(Socket socket, Packet packet) {
        super(socket);
        this.packet = packet;
    }

    @Override
    public Packet execute() throws SessionException, SQLException {
        adminSessionIsValid(packet);
        Packet response = packet.clone();

        MySQLQueryCommand logout = new AdminLogoutQuery(packet.session.uid, packet.session.session_key);
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
