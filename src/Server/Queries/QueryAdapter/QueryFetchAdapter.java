package Server.Queries.QueryAdapter;

import Server.Queries.QueryCommand.MySQLQueryCommand;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * An ADAPTER class to adapt queries fetches.
 */
public class QueryFetchAdapter {
    private final MySQLQueryCommand command;

    /**
     * Sets the base query command that will be executed and converted.
     * @param command the MySQLQueryCommand that will be executed and its result converted.
     */
    public QueryFetchAdapter(MySQLQueryCommand command) {
        this.command = command;
    }

    /**
     * Executes the command set by the constructor and converts its result.
     * @return the converted result as a list of list of strings.
     * @throws SQLException if the query in the command can't be executed.
     */
    public ArrayList<ArrayList<String>> execute() throws SQLException {
        Iterable<?> queryResult = (Iterable<?>) command.execute();
        ArrayList<ArrayList<String>> out = new ArrayList<>();

        for (Object query_row : queryResult) {
            Iterable<?> row = (Iterable<?>) query_row;

            ArrayList<String> to_add = new ArrayList<>();
            for (Object col : row) {
                to_add.add((String) col);
            }

            out.add(to_add);
        }

        return out;
    }

}
