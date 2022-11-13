package Server.Operations.OpCommands;

import Server.Queries.QueryAdapter.QueryOneResultAdapter;
import Server.Queries.QueryCommand.GetUIDQuery;
import Server.Queries.QueryCommand.MySQLQueryCommand;
import Shared.ErrorHandling.ErrorCode;
import Shared.ErrorHandling.Exceptions.SessionException;
import Shared.Packet.Packet;

import java.net.Socket;
import java.sql.SQLException;

public class AdminDeleteUserOperation extends OperationCommand {
    private final Packet packet;

    public AdminDeleteUserOperation(Socket socket, Packet packet) {
        super(socket);
        this.packet = packet;
    }

    @Override
    public Packet execute() throws SessionException, SQLException {
        adminSessionIsValid(packet);

        String username = (String) packet.data.get(0);

        MySQLQueryCommand getUid = new GetUIDQuery(username);
        QueryOneResultAdapter getUidAdpt = new QueryOneResultAdapter(getUid);

        String to_delete = getUidAdpt.execute();

        Packet response = packet.clone();
        if (to_delete == null) {
            response.isSuccessful = false;
            response.errorCode = ErrorCode.USER_NOT_FOUND;
        }
        else {
            response.isSuccessful = true;
        }

        return response;
    }
}
