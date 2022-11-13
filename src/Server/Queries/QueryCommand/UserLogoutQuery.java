package Server.Queries.QueryCommand;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserLogoutQuery extends MySQLQueryCommand {
    private final String uid;
    private final String sessionKey;

    public UserLogoutQuery(String uid, String sessionKey) {
        this.uid = uid;
        this.sessionKey = sessionKey;
    }

    @Override
    public Object execute() throws SQLException {
        String query = queriesHandler.getQuery("user_logout.sql");

        List<Object> queryParameters = new ArrayList<>();

        queryParameters.add(uid);
        queryParameters.add(sessionKey);

        return db.execUpdate(query, queryParameters);
    }
}
