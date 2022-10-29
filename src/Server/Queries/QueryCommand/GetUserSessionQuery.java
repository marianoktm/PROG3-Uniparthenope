package Server.Queries.QueryCommand;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GetUserSessionQuery extends MySQLQueryCommand {
    private final String uid;
    private final String sessionKey;

    public GetUserSessionQuery(String uid, String sessionKey) {
        this.uid = uid;
        this.sessionKey = sessionKey;
    }

    @Override
    public Object execute() throws SQLException {
        String query = queriesHandler.getQuery("get_user_session.sql");
        List<Object> queryParameters = new ArrayList<>();

        queryParameters.add(uid);
        queryParameters.add(sessionKey);

        return db.execQuery(query, queryParameters);
    }
}
