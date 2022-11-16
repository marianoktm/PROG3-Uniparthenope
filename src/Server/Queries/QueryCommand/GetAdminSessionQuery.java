package Server.Queries.QueryCommand;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GetAdminSessionQuery extends MySQLQueryCommand {
    private final String adminid;
    private final String sessionKey;

    /**
     * Sets the data used by the query.
     * @param uid the uid of the admin
     * @param sessionKey the session used by the client
     */
    public GetAdminSessionQuery(String uid, String sessionKey) {
        this.adminid = uid;
        this.sessionKey = sessionKey;
    }

    /**
     * Executes the query.
     * @return the result of the query.
     * @throws SQLException if the query cannot be executed.
     */
    @Override
    public Object execute() throws SQLException {
        String query = queriesHandler.getQuery("get_admin_session.sql");
        List<Object> queryParameters = new ArrayList<>();

        queryParameters.add(adminid);
        queryParameters.add(sessionKey);

        return db.execQuery(query, queryParameters);
    }

}
