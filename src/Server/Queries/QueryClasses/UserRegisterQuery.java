package Server.Queries.QueryClasses;

import java.util.ArrayList;
import java.util.List;

public class UserRegisterQuery extends MySQLQueryCommand {
    private final String username;
    private final String email;
    private final String pass_hash;

    public UserRegisterQuery(String username, String email, String pass_hash) {
        this.username = username;
        this.email = email;
        this.pass_hash = pass_hash;
    }


    @Override
    public Object execute() {
        String query = queriesHandler.getQuery("user_register.sql");
        List<Object> queryParameters = new ArrayList<>();

        queryParameters.add(username);
        queryParameters.add(email);
        queryParameters.add(pass_hash);

        return db.execUpdate(query, queryParameters);
    }
}
