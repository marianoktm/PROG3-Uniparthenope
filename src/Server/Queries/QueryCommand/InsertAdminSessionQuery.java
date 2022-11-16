package Server.Queries.QueryCommand;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InsertAdminSessionQuery extends MySQLQueryCommand {
    private final String adminId;
    private final String sessionKey;

    /**
     * Sets the data used by the query.
     * @param adminId the admin id that is logging in
     * @param sessionKey the session key that will be recorded
     */
    public InsertAdminSessionQuery(String adminId, String sessionKey) {
        this.adminId = adminId;
        this.sessionKey = sessionKey;
    }

    /**
     * Executes the query.
     * @return the result of the query.
     * @throws SQLException if the query cannot be executed.
     */
    @Override
    public Object execute() throws SQLException {
        String query = queriesHandler.getQuery("insert_admin_session.sql");
        List<Object> queryParameters = new ArrayList<>();

        queryParameters.add(adminId);
        queryParameters.add(sessionKey);

        return db.execUpdate(query, queryParameters);
    }
}
