package Server.Queries.QueryClasses;

import java.util.ArrayList;
import java.util.List;

public class FetchHashtagsQuery extends MySQLQueryCommand {
    private final String hashtag;
    private final int maxFetch;

    public FetchHashtagsQuery(String hashtag) {
        this.hashtag = hashtag;
        this.maxFetch = 0;
    }

    public FetchHashtagsQuery(String hashtag, int maxFetch) {
        this.hashtag = hashtag;
        this.maxFetch = maxFetch;
    }

    @Override
    public Object execute() {
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
