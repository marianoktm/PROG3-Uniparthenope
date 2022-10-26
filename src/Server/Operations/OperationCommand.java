package Server.Operations;

import Server.Queries.QueriesAdapter.QueryFetchAdapter;
import Server.Queries.QueryClasses.GetUserSessionQuery;
import Server.Queries.QueryClasses.MySQLQueryCommand;
import Shared.ErrorHandling.Exceptions.InvalidSessionException;
import Shared.Packet.Packet;
import Shared.Packet.Session;

import java.net.Socket;
import java.util.ArrayList;

public abstract class OperationCommand {
    protected Socket socket;

    OperationCommand(Socket socket) {
        this.socket = socket;
    }

    public abstract Packet execute() throws InvalidSessionException;

    protected static boolean sessionIsValid(Packet packet) throws InvalidSessionException {
        Session session = packet.session;
        MySQLQueryCommand mySQLQueryCommand = new GetUserSessionQuery(session.uid, session.session_key);
        QueryFetchAdapter queryFetchAdapter = new QueryFetchAdapter(mySQLQueryCommand);

        ArrayList<ArrayList<String>> fetchedSession = new ArrayList<>();
        fetchedSession = queryFetchAdapter.execute();

        if (fetchedSession == null)
            throw new InvalidSessionException("The session is not valid!");
        else
            return true;
    }
}
