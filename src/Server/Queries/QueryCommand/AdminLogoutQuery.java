package Server.Queries.QueryCommand;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminLogoutQuery extends MySQLQueryCommand {
    private final String adminId;
    private final String sessionKey;

    public AdminLogoutQuery(String adminId, String sessionKey) {
        this.adminId = adminId;
        this.sessionKey = sessionKey;
    }

    @Override
    public Object execute() throws SQLException {
        String query = queriesHandler.getQuery("admin_logout.sql");

        List<Object> queryParameters = new ArrayList<>();

        queryParameters.add(adminId);
        queryParameters.add(sessionKey);

        return db.execUpdate(query, queryParameters);
    }
}
