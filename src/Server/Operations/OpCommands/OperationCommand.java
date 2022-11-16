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
 * A COMMAND class that provides signatures for mandatory methods of COMMAND and boilerplate or useful code.
 */
public abstract class OperationCommand {
    protected Socket socket;

    /**
     * Sets the socket where the packets needed by the command will be read or sent.
     * @param socket the socket where packets will be sent or read.
     */
    OperationCommand(Socket socket) {
        this.socket = socket;
    }

    /**
     * Executes the current command.
     * @return a response Packet.
     * @throws SessionException if the session provided by the client is invalid.
     * @throws SQLException if a query cannot be executed.
     * @throws BanException if a user tries to log in while banned.
     */
    public abstract Packet execute() throws SessionException, SQLException, BanException;

    /**
     * Checks if the user session is valid. Use it if a user must be logged in to perform a certain operation.
     * @param packet the packet where the session is stored.
     * @return true if the user is logged in, false otherwise.
     * @throws SessionException if the session is invalid.
     * @throws SQLException if the fetch session query can't be executed.
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
     * Checks if the admin session is valid. Use it if an admin must be logged in to perform a certain operation.
     * @param packet the packet where the session is stored.
     * @return true if the admin is logged in, false otherwise.
     * @throws SessionException if the session is invalid.
     * @throws SQLException if the fetch admin session query can't be executed.
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
