package Server.Operations;

import Server.Auth.RegisterHandler;
import Server.Queries.QueriesAdapter.QueryFetchAdapter;
import Server.Queries.QueryClasses.GetUserDataQuery;
import Server.Queries.QueryClasses.MySQLQueryCommand;
import Server.Queries.QueryClasses.UserRegisterQuery;
import Shared.Packet;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Op0Register extends OperationCommand {
    private Packet packet;

    public Op0Register(Socket socket, Packet packet) {
        super(socket);
        this.packet = packet;
    }

    @Override
    public Packet execute() {
        String username = (String) packet.data.get(0);

        MySQLQueryCommand checkUserExists = new GetUserDataQuery(username);
        QueryFetchAdapter checkUserExistsAdpt = new QueryFetchAdapter(checkUserExists);

        Packet response = packet.clone();

        List<?> result = checkUserExistsAdpt.execute();
        if (result.isEmpty()) {
            response.bool = true;

            RegisterHandler handler = RegisterHandler.getInstance();

            handler.register(((String) packet.data.get(0)),(String) packet.data.get(1), (String) packet.data.get(2));
        }
        else {
            response.bool = false;
        }

        return response;
    }
}
