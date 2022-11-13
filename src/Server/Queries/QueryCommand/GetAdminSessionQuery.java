package Server.Queries.QueryCommand;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GetAdminSessionQuery extends MySQLQueryCommand {
    private final String adminid;
    private final String sessionKey;

    public GetAdminSessionQuery(String uid, String sessionKey) {
        this.adminid = uid;
        this.sessionKey = sessionKey;
    }

    @Override
    public Object execute() throws SQLException {
        String query = queriesHandler.getQuery("get_admin_session.sql");
        List<Object> queryParameters = new ArrayList<>();

        queryParameters.add(adminid);
        queryParameters.add(sessionKey);

        return db.execQuery(query, queryParameters);
    }

}
