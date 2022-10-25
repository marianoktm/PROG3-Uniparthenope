package Server.Queries.QueryClasses;

import java.util.ArrayList;
import java.util.List;

public class UserUnfollowQuery extends MySQLQueryCommand {
    private final String unfollowing_id;
    private final String unfollowed_id;
    
    public UserUnfollowQuery(String unfollowing_id, String unfollowed_id) {
        this.unfollowing_id = unfollowing_id;
        this.unfollowed_id = unfollowed_id;
    }
    
    @Override
    public Object execute() {
        String query = queriesHandler.getQuery("user_unfollow.sql");
        List<Object> queryParameters = new ArrayList<>();
        
        queryParameters.add(unfollowing_id);
        queryParameters.add(unfollowed_id);
        
        return db.execUpdate(query, queryParameters);
    }
}
