package Server.Queries.QueryCommand;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserUnfollowQuery extends MySQLQueryCommand {
    private final String unfollowing_id;
    private final String unfollowed_id;

    /**
     * Sets the data used by the query.
     * @param unfollowing_id the id of the user that is unfollowing someone
     * @param unfollowed_id the id of the unfollowed user
     */
    public UserUnfollowQuery(String unfollowing_id, String unfollowed_id) {
        this.unfollowing_id = unfollowing_id;
        this.unfollowed_id = unfollowed_id;
    }

    /**
     * Executes the query.
     * @return the result of the query.
     * @throws SQLException if the query cannot be executed.
     */
    @Override
    public Object execute() throws SQLException {
        String query = queriesHandler.getQuery("user_unfollow.sql");
        List<Object> queryParameters = new ArrayList<>();
        
        queryParameters.add(unfollowing_id);
        queryParameters.add(unfollowed_id);
        
        return db.execUpdate(query, queryParameters);
    }
}
