package Server.Queries.QueryCommand;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FetchFollowedTweetsQuery extends MySQLQueryCommand {
    private final String uid;

    /**
     * Sets the data used by the query.
     * @param uid the uid of the user asking for its followers tweets.
     */
    public FetchFollowedTweetsQuery(String uid) {
        this.uid = uid;
    }

    /**
     * Executes the query.
     * @return the result of the query.
     * @throws SQLException if the query cannot be executed.
     */
    @Override
    public Object execute() throws SQLException {
        String query = queriesHandler.getQuery("fetch_followed_tweets.sql");
        List<Object> queryParameters = new ArrayList<>();

        queryParameters.add(uid);

        return db.execQuery(query, queryParameters);
    }
}
