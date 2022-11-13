package Server.Operations.OpCommands;

import Server.Queries.QueryAdapter.QueryFetchAdapter;
import Server.Queries.QueryCommand.FetchHashtagsQuery;
import Server.Queries.QueryCommand.MySQLQueryCommand;
import Shared.ErrorHandling.ErrorCode;
import Shared.ErrorHandling.Exceptions.SessionException;
import Shared.Packet.Packet;

import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;

public class FetchTweetByHashtagOperation extends OperationCommand {
    private final Packet packet;

    public FetchTweetByHashtagOperation(Socket socket, Packet packet) {
        super(socket);
        this.packet = packet;
    }

    @Override
    public Packet execute() throws SessionException, SQLException {
        adminSessionIsValid(packet);

        String hashtag = (String) packet.data.get(0);

        MySQLQueryCommand fetchTweets = new FetchHashtagsQuery(hashtag);
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
