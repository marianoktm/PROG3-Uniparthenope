package Server.Queries.QueryAdapter;

import Server.Queries.QueryCommand.MySQLQueryCommand;

import java.sql.SQLException;

/**
 * An ADAPTER class to adapt update queries.
 */
public class QueryUpdateAdapter {
    private final MySQLQueryCommand command;

    /**
     * Sets the base query command that will be executed and converted.
     * @param command the MySQLQueryCommand that will be executed and its result converted.
     */
    public QueryUpdateAdapter(MySQLQueryCommand command) {
        this.command = command;
    }

    /**
     * Executes the command set by the constructor and converts its result.
     * @return the number of rows updated.
     * @throws SQLException if the query in the command can't be executed.
     */
    public int execute() throws SQLException {
        return (int) command.execute();
    }
}
