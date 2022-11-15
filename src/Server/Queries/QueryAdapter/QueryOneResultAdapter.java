package Server.Queries.QueryAdapter;

import Server.Queries.QueryCommand.MySQLQueryCommand;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 */
public class QueryOneResultAdapter {
    private final QueryFetchAdapter fetch;

    /**
     * @param command
     */
    public QueryOneResultAdapter(MySQLQueryCommand command) {
        this.fetch = new QueryFetchAdapter(command);
    }

    /**
     * @return
     * @throws SQLException
     */
    public String execute() throws SQLException {
        ArrayList<ArrayList<String>> out;
        out = fetch.execute();

        if (out.isEmpty()) return null;
        else return out.get(0).get(0);
    }
}
