package Server.Queries.QueryCommand;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FetchFollowedTweetsQuery extends MySQLQueryCommand {
    private final String uid;

    public FetchFollowedTweetsQuery(String uid) {
        this.uid = uid;
    }

    @Override
    public Object execute() throws SQLException {
        String query = queriesHandler.getQuery("fetch_followed_tweets.sql");
        List<Object> queryParameters = new ArrayList<>();

        queryParameters.add(uid);

        return db.execQuery(query, queryParameters);
    }
}
