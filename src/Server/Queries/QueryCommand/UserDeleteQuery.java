package Server.Queries.QueryCommand;

import Server.Queries.QueryAdapter.QueryOneResultAdapter;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDeleteQuery extends MySQLQueryCommand {
    private final String uid;

    /**
     * Sets the data used by the query.
     * @param username the user that will be banned
     * @throws SQLException if a query can't be executed
     */
    public UserDeleteQuery(String username) throws SQLException {
        MySQLQueryCommand getuid = new GetUIDQuery(username);
        QueryOneResultAdapter oneResultAdapter = new QueryOneResultAdapter(getuid);

        uid = oneResultAdapter.execute();
    }

    /**
     * Executes the query.
     * @return the result of the query.
     * @throws SQLException if the query cannot be executed.
     */
    @Override
    public Object execute() throws SQLException {
        String query = queriesHandler.getQuery("user_delete.sql");
        List<Object> queryParameters = new ArrayList<>();

        queryParameters.add(uid);

        return db.execUpdate(query, queryParameters);
    }
}
