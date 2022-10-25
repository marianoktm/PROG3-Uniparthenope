package Server.Queries.QueryClasses;

import Server.Queries.QueriesAdapter.QueryOnerResultAdapter;

import java.util.ArrayList;
import java.util.List;

public class SubmitTweet extends MySQLQueryCommand {
    private final String hashtag;
    private final String message;
    private final String uid;

    public SubmitTweet(String hashtag, String message, String username) {
        this.hashtag = hashtag;
        this.message = message;

        MySQLQueryCommand query = new GetUIDQuery(username);
        QueryOnerResultAdapter oneResFetch = new QueryOnerResultAdapter(query);

        this.uid = oneResFetch.execute();
    }

    @Override
    public Object execute() {
        String query = queriesHandler.getQuery("submit_tweet.sql");
        List<Object> queryParameters = new ArrayList<>();

        queryParameters.add(uid);
        queryParameters.add(message);
        queryParameters.add(hashtag);


        return db.execUpdate(query, queryParameters);
    }
}
