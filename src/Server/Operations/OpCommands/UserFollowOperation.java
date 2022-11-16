package Server.Operations.OpCommands;

import Server.Queries.QueryAdapter.QueryOneResultAdapter;
import Server.Queries.QueryAdapter.QueryUpdateAdapter;
import Server.Queries.QueryCommand.GetUIDQuery;
import Server.Queries.QueryCommand.MySQLQueryCommand;
import Server.Queries.QueryCommand.UserFollowQuery;
import Shared.ErrorHandling.ErrorCode;
import Shared.ErrorHandling.Exceptions.SessionException;
import Shared.Packet.Packet;

import java.net.Socket;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

public class UserFollowOperation extends OperationCommand{
    private final Packet packet;

    /**
     * Sets both the socket and the packet needed in order to execute the operation.
     * @param socket the socket where packets will be sent or read.
     * @param packet the packet needed for the operation.
     */
    public UserFollowOperation(Socket socket, Packet packet) {
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
        // Check session, if invalid throws exception
        sessionIsValid(packet);

        Packet response = packet.clone();

        // Get following id
        String following_id = packet.session.uid;

        // Get followed id
        MySQLQueryCommand followedQuery = new GetUIDQuery((String) packet.data.get(0));
        QueryOneResultAdapter followedQueryAdpt = new QueryOneResultAdapter(followedQuery);

        String followed_id = followedQueryAdpt.execute();

        // If user does not exists, error
        if (following_id == null || followed_id == null) {
            response.isSuccessful = false;
            response.errorCode = ErrorCode.USER_NOT_FOUND;
            return response;
        }

        // Follow Query
        MySQLQueryCommand followQuery = new UserFollowQuery(following_id, followed_id);
        QueryUpdateAdapter followQueryAdpt = new QueryUpdateAdapter(followQuery);

        // If execution of query returns 0, record already in table so "following" already follows "followed".
        try {
            followQueryAdpt.execute();
            response.isSuccessful = true;
        }
        catch (SQLIntegrityConstraintViolationException e) {
            response.isSuccessful = false;
            response.errorCode = ErrorCode.ALREADY_FOLLOWING;
        }

        return response;
    }
}
