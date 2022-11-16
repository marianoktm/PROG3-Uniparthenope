package Server.Operations.OpCommands;

import Server.Queries.QueryAdapter.QueryFetchAdapter;
import Server.Queries.QueryCommand.FetchFollowedTweetsQuery;
import Server.Queries.QueryCommand.MySQLQueryCommand;
import Shared.ErrorHandling.ErrorCode;
import Shared.ErrorHandling.Exceptions.SessionException;
import Shared.Packet.Packet;

import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserFetchFollowedTweetsOperation extends OperationCommand {
    private final Packet packet;

    /**
     * Sets both the socket and the packet needed in order to execute the operation.
     * @param socket the socket where packets will be sent or read.
     * @param packet the packet needed for the operation.
     */
    public UserFetchFollowedTweetsOperation(Socket socket, Packet packet) {
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

        String uid = packet.session.uid;

        MySQLQueryCommand fetchTweets = new FetchFollowedTweetsQuery(uid);
        QueryFetchAdapter fetchTweetsAdpt = new QueryFetchAdapter(fetchTweets);

        ArrayList<ArrayList<String>> result = fetchTweetsAdpt.execute();
        Packet response = packet.clone();

        if (result.isEmpty()) {
            response.isSuccessful = false;
            response.errorCode = ErrorCode.FETCH_TWEETS_ERROR;
        }
        else {
            response.isSuccessful = true;
            response.data = result;
        }

        return response;
    }
}
