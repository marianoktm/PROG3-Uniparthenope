package Server.Operations.OpCommands;

import Server.Queries.QueryAdapter.QueryOneRowAdapter;
import Server.Queries.QueryAdapter.QueryUpdateAdapter;
import Server.Queries.QueryCommand.GetAdminDataQuery;
import Server.Queries.QueryCommand.InsertAdminSessionQuery;
import Server.Queries.QueryCommand.MySQLQueryCommand;
import Server.Utils.TwitterServerUtils;
import Shared.ErrorHandling.ErrorCode;
import Shared.ErrorHandling.Exceptions.SessionException;
import Shared.Packet.Packet;
import org.apache.commons.codec.digest.DigestUtils;

import java.net.Socket;
import java.sql.SQLException;
import java.util.List;

public class AdminLoginOperation extends OperationCommand  {
    private final Packet packet;

    public AdminLoginOperation(Socket socket, Packet packet) {
        super(socket);
        this.packet = packet;
    }

    @Override
    public Packet execute() throws SessionException, SQLException {
        Packet response = packet.clone();

        // data 0 = admin id, data 1 = password
        String adminId = (String) packet.data.get(0);
        String password = (String) packet.data.get(1);

        MySQLQueryCommand adminData = new GetAdminDataQuery(adminId);
        QueryOneRowAdapter adminDataAdpt = new QueryOneRowAdapter(adminData);

        List<?> result = adminDataAdpt.execute();
        if (result.isEmpty()) {
            response.isSuccessful = false;
            response.errorCode = ErrorCode.LOGIN_USER_NOT_FOUND;
        }
        else {
            String passwordHash = DigestUtils.sha256Hex(password);

            // get 0 = admin id, get 1 = password
            if (result.get(1).equals(passwordHash)) {
                String sessionKey = TwitterServerUtils.sessionKeyGenerator();

                MySQLQueryCommand insertSession = new InsertAdminSessionQuery(adminId, sessionKey);
                QueryUpdateAdapter sessionQuery = new QueryUpdateAdapter(insertSession);

                if (sessionQuery.execute() != 0) {
                    response.isSuccessful = true;
                    response.session.uid = adminId;
                    response.session.session_key = sessionKey;
                }
                else {
                    response.isSuccessful = false;
                    response.errorCode = ErrorCode.SESSION_ERROR;
                    throw new SessionException("Error on session insert!");
                }
            }
            else {
                response.isSuccessful = false;
                response.errorCode = ErrorCode.ADMIN_WRONG_PASSWORD;
            }
        }

        return response;
    }
}
