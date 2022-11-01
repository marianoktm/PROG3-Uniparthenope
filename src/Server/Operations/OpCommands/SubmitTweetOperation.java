package Server.Operations.OpCommands;

import Server.Queries.QueryAdapter.QueryUpdateAdapter;
import Server.Queries.QueryCommand.MySQLQueryCommand;
import Server.Queries.QueryCommand.SubmitTweetQuery;
import Shared.ErrorHandling.ErrorCode;
import Shared.ErrorHandling.Exceptions.SessionException;
import Shared.Packet.Packet;

import java.net.Socket;
import java.sql.SQLException;

public class SubmitTweetOperation extends OperationCommand {
    private final Packet packet;

    public SubmitTweetOperation(Socket socket, Packet packet) {
        super(socket);
        this.packet = packet;
    }

    @Override
    public Packet execute() throws SessionException, SQLException {
        sessionIsValid(packet);

        Packet response = packet.clone();

        String hashtag = (String) packet.data.get(0);
        String message = (String) packet.data.get(1);
        String username = packet.session.username;


        // Tweet query insert
        MySQLQueryCommand tweetInsert = new SubmitTweetQuery(hashtag, message, username);
        QueryUpdateAdapter tweetInsertAdpt = new QueryUpdateAdapter(tweetInsert);

        if (tweetInsertAdpt.execute() != 0) {
            response.isSuccessful = true;
        }
        else {
            response.isSuccessful = false;
            response.errorCode = ErrorCode.SUBMIT_TWEET_ERROR;
        }

        return response;
    }
}
