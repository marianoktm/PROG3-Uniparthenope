package Server.Queries.QueryCommand;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FetchHashtagsQuery extends MySQLQueryCommand {
    private final String hashtag;
    private final int maxFetch;

    /**
     * Sets the data used by the query.
     * @param hashtag the hashtag to be fetched.
     */
    public FetchHashtagsQuery(String hashtag) {
        this.hashtag = hashtag;
        this.maxFetch = 0;
    }

    /**
     * Sets the data used by the query.
     * @param hashtag the hashtag to be fetched.
     * @param maxFetch the max number of fetches.
     */
    public FetchHashtagsQuery(String hashtag, int maxFetch) {
        this.hashtag = hashtag;
        this.maxFetch = maxFetch;
    }

    /**
     * Executes the query.
     * @return the result of the query.
     * @throws SQLException if the query cannot be executed.
     */
    @Override
    public Object execute() throws SQLException {
        String query = queriesHandler.getQuery("fetch_hashtags.sql");
        List<Object> queryParameters = new ArrayList<>();

        queryParameters.add(hashtag);

        if (maxFetch <= 0)
            queryParameters.add(1024);
        else
            queryParameters.add(maxFetch);


        return db.execQuery(query, queryParameters);
    }
}
