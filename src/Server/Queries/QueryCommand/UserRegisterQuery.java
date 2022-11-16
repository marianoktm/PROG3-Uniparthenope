package Server.Queries.QueryCommand;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRegisterQuery extends MySQLQueryCommand {
    private final String username;
    private final String email;
    private final String pass_hash;

    /**
     * Sets the data used by the query.
     * @param username the register username
     * @param email the register email
     * @param pass_hash the register password in sha256 hash.
     */
    public UserRegisterQuery(String username, String email, String pass_hash) {
        this.username = username;
        this.email = email;
        this.pass_hash = pass_hash;
    }

    /**
     * Executes the query.
     * @return the result of the query.
     * @throws SQLException if the query cannot be executed.
     */
    @Override
    public Object execute() throws SQLException {
        String query = queriesHandler.getQuery("user_register.sql");
        List<Object> queryParameters = new ArrayList<>();

        queryParameters.add(username);
        queryParameters.add(email);
        queryParameters.add(pass_hash);

        return db.execUpdate(query, queryParameters);
    }
}
