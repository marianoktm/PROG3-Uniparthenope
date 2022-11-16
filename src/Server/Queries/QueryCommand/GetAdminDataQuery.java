package Server.Queries.QueryCommand;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GetAdminDataQuery extends MySQLQueryCommand {
    private final String admin_id;

    /**
     * Sets the data used by the query.
     * @param admin_id the id of the admin
     */
    public GetAdminDataQuery(String admin_id) {
        this.admin_id = admin_id;
    }

    /**
     * Executes the query.
     * @return the result of the query.
     * @throws SQLException if the query cannot be executed.
     */
    @Override
    public Object execute() throws SQLException {
        String query = queriesHandler.getQuery("get_admin_data.sql");
        List<Object> queryParameters = new ArrayList<>();

        queryParameters.add(admin_id);

        return db.execQuery(query, queryParameters);
    }
}
