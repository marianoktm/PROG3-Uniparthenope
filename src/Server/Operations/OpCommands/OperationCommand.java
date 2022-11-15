package Server.Operations.OpCommands;

import Server.Queries.QueryAdapter.QueryFetchAdapter;
import Server.Queries.QueryCommand.GetAdminSessionQuery;
import Server.Queries.QueryCommand.GetUserSessionQuery;
import Server.Queries.QueryCommand.MySQLQueryCommand;
import Shared.ErrorHandling.Exceptions.BanException;
import Shared.ErrorHandling.Exceptions.SessionException;
import Shared.Packet.Packet;
import Shared.Packet.Session;

import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 */
public abstract class OperationCommand {
    protected Socket socket;

    /**
     * @param socket
     */
    OperationCommand(Socket socket) {
        this.socket = socket;
    }

    /**
     * @return
     * @throws SessionException
     * @throws SQLException
     * @throws BanException
     */
    public abstract Packet execute() throws SessionException, SQLException, BanException;

    /**
     * @param packet
     * @return
     * @throws SessionException
     * @throws SQLException
     */
    protected static boolean sessionIsValid(Packet packet) throws SessionException, SQLException {
        if (packet.session == null)
            throw new SessionException("The session is not valid!");

        Session session = packet.session;
        MySQLQueryCommand mySQLQueryCommand = new GetUserSessionQuery(session.uid, session.session_key);
        QueryFetchAdapter queryFetchAdapter = new QueryFetchAdapter(mySQLQueryCommand);

        ArrayList<ArrayList<String>> fetchedSession;
        fetchedSession = queryFetchAdapter.execute();

        if (fetchedSession == null)
            throw new SessionException("Session invalid or Expired.");
        else
            return true;
    }

    /**
     * @param packet
     * @return
     * @throws SessionException
     * @throws SQLException
     */
    protected static boolean adminSessionIsValid(Packet packet) throws SessionException, SQLException {
        if (packet.session == null)
            throw new SessionException("The session is not valid!");

        Session session = packet.session;
        MySQLQueryCommand mySQLQueryCommand = new GetAdminSessionQuery(session.uid, session.session_key);
        QueryFetchAdapter queryFetchAdapter = new QueryFetchAdapter(mySQLQueryCommand);

        ArrayList<ArrayList<String>> fetchedSession;
        fetchedSession = queryFetchAdapter.execute();

        if (fetchedSession == null)
            throw new SessionException("Session invalid or Expired.");
        else
            return true;
    }
}
