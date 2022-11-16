package Server.Queries.QueryCommand;

import Server.Queries.QueryAdapter.QueryOneResultAdapter;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SubmitTweetQuery extends MySQLQueryCommand {
    private final String hashtag;
    private final String message;
    private final String uid;

    /**
     * Sets the data used by the query.
     * @param hashtag the tweet hashtag
     * @param message the actual message
     * @param username the tweet's sender
     * @throws SQLException if a query cannot be executed.
     */
    public SubmitTweetQuery(String hashtag, String message, String username) throws SQLException {
        this.hashtag = hashtag;
        this.message = message;

        MySQLQueryCommand query = new GetUIDQuery(username);
        QueryOneResultAdapter oneResFetch = new QueryOneResultAdapter(query);

        this.uid = oneResFetch.execute();
    }

    /**
     * Executes the query.
     * @return the result of the query.
     * @throws SQLException if the query cannot be executed.
     */
    @Override
    public Object execute() throws SQLException {
        String query;
        List<Object> queryParameters = new ArrayList<>();

        queryParameters.add(uid);
        queryParameters.add(message);

        if (hashtag != null) {
            query = queriesHandler.getQuery("submit_tweet.sql");
            queryParameters.add(hashtag);
        }
        else
            query = queriesHandler.getQuery("submit_tweet_nohashtag.sql");

        return db.execUpdate(query, queryParameters);
    }
}
