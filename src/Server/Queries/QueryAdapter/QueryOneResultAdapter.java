package Server.Queries.QueryAdapter;

import Server.Queries.QueryCommand.MySQLQueryCommand;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * An ADAPTER class to adapt queries with only one result.
 */
public class QueryOneResultAdapter {
    private final QueryFetchAdapter fetch;

    /**
     * Sets the base query command that will be executed and converted.
     * @param command the MySQLQueryCommand that will be executed and its result converted.
     */
    public QueryOneResultAdapter(MySQLQueryCommand command) {
        this.fetch = new QueryFetchAdapter(command);
    }

    /**
     * Executes the command set by the constructor and converts its result.
     * @return the converted result as a String.
     * @throws SQLException if the query in the command can't be executed.
     */
    public String execute() throws SQLException {
        ArrayList<ArrayList<String>> out;
        out = fetch.execute();

        if (out.isEmpty()) return null;
        else return out.get(0).get(0);
    }
}
