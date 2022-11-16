package Server.Queries.QueryCommand;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GetUserDataQuery extends MySQLQueryCommand {
    private final String username;

    /**
     * Sets the data used by the query.
     * @param username the username of the user
     */
    public GetUserDataQuery(String username) {
        this.username = username;
    }

    /**
     * Executes the query.
     * @return the result of the query.
     * @throws SQLException if the query cannot be executed.
     */
    @Override
    public Object execute() throws SQLException {
        String query = queriesHandler.getQuery("get_user_data.sql");
        List<Object> queryParameters = new ArrayList<>();

        queryParameters.add(username);

        return db.execQuery(query, queryParameters);
    }
}
