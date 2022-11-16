package Server.Queries.QueryCommand;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserFollowQuery extends MySQLQueryCommand {
    private final String follower_id;
    private final String followed_id;

    /**
     * Sets the data used by the query.
     * @param follower_id the user following.
     * @param followed_id the user being followed.
     */
    public UserFollowQuery(String follower_id, String followed_id) {
        this.follower_id = follower_id;
        this.followed_id = followed_id;
    }

    /**
     * Executes the query.
     * @return the result of the query.
     * @throws SQLException if the query cannot be executed.
     */
    @Override
    public Object execute() throws SQLException {
        String query = queriesHandler.getQuery("user_follow.sql");
        List<Object> queryParameters = new ArrayList<>();

        queryParameters.add(follower_id);
        queryParameters.add(followed_id);

        return db.execUpdate(query, queryParameters);
    }
}
