package Server.Operations.OpCommands;

import Server.Queries.QueryAdapter.QueryOneRowAdapter;
import Server.Queries.QueryAdapter.QueryUpdateAdapter;
import Server.Queries.QueryCommand.GetUserDataQuery;
import Server.Queries.QueryCommand.MySQLQueryCommand;
import Server.Queries.QueryCommand.UserRegisterQuery;
import Shared.ErrorHandling.ErrorCode;
import Shared.ErrorHandling.Exceptions.InvalidSessionException;
import Shared.Packet.Packet;
import org.apache.commons.codec.digest.DigestUtils;

import java.net.Socket;
import java.sql.SQLException;
import java.util.List;

public class UserRegisterOperation extends OperationCommand {
    private final Packet packet;

    /**
     * Sets both the socket and the packet needed in order to execute the operation.
     * @param socket the socket where packets will be sent or read.
     * @param packet the packet needed for the operation.
     */
    public UserRegisterOperation(Socket socket, Packet packet) {
        super(socket);
        this.packet = packet;
    }

    /**
     * Executes the current command.
     * @return a response Packet.
     * @throws InvalidSessionException if the session provided by the client is invalid.
     * @throws SQLException if a query cannot be executed.
     */
    @Override
    public Packet execute() throws InvalidSessionException, SQLException {
        Packet response = packet.clone();
        String username;

        // Parameters check
        try {
             username = (String) packet.data.get(0);
        }
        catch (NullPointerException e) {
            e.printStackTrace();
            response.isSuccessful = false;
            response.errorCode = ErrorCode.NO_PARAMETERS;

            return response;
        }

        // Query creation
        MySQLQueryCommand checkUserExists = new GetUserDataQuery(username);
        QueryOneRowAdapter checkUserExistsAdpt = new QueryOneRowAdapter(checkUserExists);

        // If no query result, user does not exists and registration can happen, else error
        List<?> result = checkUserExistsAdpt.execute();
        if (result.isEmpty()) {
            response.isSuccessful = true;

            // data 0 = username, data 1 = email, data 2 = password
            // String username already defined
            String email = (String) packet.data.get(1);
            String password = (String) packet.data.get(2);

            String passwordHash = DigestUtils.sha256Hex(password);

            MySQLQueryCommand query = new UserRegisterQuery(username, email, passwordHash);
            QueryUpdateAdapter registerQuery = new QueryUpdateAdapter(query);

            registerQuery.execute();
        }
        else {
            response.isSuccessful = false;
            response.errorCode = ErrorCode.REGISTER_USER_EXISTS;
        }

        return response;
    }
}
