package Server.Queries.QueryAdapter;

import Server.Queries.QueryCommand.MySQLQueryCommand;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * An ADAPTER class to adapt queries with only one row results..
 */
public class QueryOneRowAdapter {
    private final QueryFetchAdapter fetch;

    /**
     * Sets the base query command that will be executed and converted.
     * @param command the MySQLQueryCommand that will be executed and its result converted.
     */
    public QueryOneRowAdapter(MySQLQueryCommand command) {
        this.fetch = new QueryFetchAdapter(command);
    }

    /**
     * Executes the command set by the constructor and converts its result.
     * @return the converted result as a List of strings.
     * @throws SQLException if the query in the command can't be executed.
     */
    public ArrayList<?> execute() throws SQLException {
        ArrayList<ArrayList<String>> out;
        out = fetch.execute();

        if (out.isEmpty()) return new ArrayList<>();
        else return out.get(0);
    }
}
