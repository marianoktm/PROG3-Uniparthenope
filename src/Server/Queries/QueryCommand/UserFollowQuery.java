package Server.Queries.QueryCommand;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserFollowQuery extends MySQLQueryCommand {
    private final String follower_id;
    private final String followed_id;

    public UserFollowQuery(String follower_id, String followed_id) {
        this.follower_id = follower_id;
        this.followed_id = followed_id;
    }

    @Override
    public Object execute() throws SQLException {
        String query = queriesHandler.getQuery("user_follow.sql");
        List<Object> queryParameters = new ArrayList<>();

        queryParameters.add(follower_id);
        queryParameters.add(followed_id);

        return db.execUpdate(query, queryParameters);
    }
}
