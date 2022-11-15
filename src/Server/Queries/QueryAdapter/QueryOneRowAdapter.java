package Server.Queries.QueryAdapter;

import Server.Queries.QueryCommand.MySQLQueryCommand;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 */
public class QueryOneRowAdapter {
    private final QueryFetchAdapter fetch;

    /**
     * @param command
     */
    public QueryOneRowAdapter(MySQLQueryCommand command) {
        this.fetch = new QueryFetchAdapter(command);
    }

    /**
     * @return
     * @throws SQLException
     */
    public ArrayList<?> execute() throws SQLException {
        ArrayList<ArrayList<String>> out;
        out = fetch.execute();

        if (out.isEmpty()) return new ArrayList<>();
        else return out.get(0);
    }
}
