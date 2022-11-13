package Server.Queries.QueryCommand;

import Server.Queries.QueryAdapter.QueryOneResultAdapter;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDeleteQuery extends MySQLQueryCommand {
    private final String uid;

    public UserDeleteQuery(String username) throws SQLException {
        MySQLQueryCommand getuid = new GetUIDQuery(username);
        QueryOneResultAdapter oneResultAdapter = new QueryOneResultAdapter(getuid);

        uid = oneResultAdapter.execute();
    }

    @Override
    public Object execute() throws SQLException {
        String query = queriesHandler.getQuery("user_delete.sql");
        List<Object> queryParameters = new ArrayList<>();

        queryParameters.add(uid);

        return db.execUpdate(query, queryParameters);
    }
}
