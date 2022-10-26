package Server.Operations;

import Server.Auth.RegisterHandler;
import Server.Queries.QueriesAdapter.QueryFetchAdapter;
import Server.Queries.QueryClasses.GetUserDataQuery;
import Server.Queries.QueryClasses.MySQLQueryCommand;
import Shared.ErrorHandling.ErrorCode;
import Shared.ErrorHandling.Exceptions.InvalidSessionException;
import Shared.Packet.Packet;

import java.net.Socket;
import java.util.List;

public class Op0Register extends OperationCommand {
    private Packet packet;

    public Op0Register(Socket socket, Packet packet) {
        super(socket);
        this.packet = packet;
    }

    @Override
    public Packet execute() throws InvalidSessionException {
        sessionIsValid(packet);

        String username = (String) packet.data.get(0);

        MySQLQueryCommand checkUserExists = new GetUserDataQuery(username);
        QueryFetchAdapter checkUserExistsAdpt = new QueryFetchAdapter(checkUserExists);

        Packet response = packet.clone();

        List<?> result = checkUserExistsAdpt.execute();
        if (result.isEmpty()) {
            response.isSuccessful = true;

            RegisterHandler handler = RegisterHandler.getInstance();

            handler.register(((String) packet.data.get(0)),(String) packet.data.get(1), (String) packet.data.get(2));
        }
        else {
            response.isSuccessful = false;
            response.errorCode = ErrorCode.REGISTER_USER_EXISTS;
        }

        return response;
    }
}
