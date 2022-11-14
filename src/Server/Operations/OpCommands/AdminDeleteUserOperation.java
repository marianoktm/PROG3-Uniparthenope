package Server.Operations.OpCommands;

import Server.Queries.QueryAdapter.QueryUpdateAdapter;
import Server.Queries.QueryCommand.MySQLQueryCommand;
import Server.Queries.QueryCommand.SubmitTweetQuery;
import Server.Queries.QueryCommand.UserDeleteQuery;
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

        MySQLQueryCommand userDelete = new UserDeleteQuery(username);
        QueryUpdateAdapter userDeleteAdpt = new QueryUpdateAdapter(userDelete);

        Packet response = packet.clone();
        if (userDeleteAdpt.execute() == 0) {
            response.isSuccessful = false;
            response.errorCode = ErrorCode.USER_NOT_FOUND;
        }
        else {
            response.isSuccessful = true;

            String hashtag = "ADMIN";
            String message = "This user was banned by an admin.";

            MySQLQueryCommand tweetInsert = new SubmitTweetQuery(hashtag, message, username);
            QueryUpdateAdapter tweetInsertAdpt = new QueryUpdateAdapter(tweetInsert);

            if (tweetInsertAdpt.execute() != 0) {
                response.isSuccessful = true;
            }
            else {
                response.isSuccessful = false;
                response.errorCode = ErrorCode.SUBMIT_TWEET_ERROR;
            }
        }

        return response;
    }
}
