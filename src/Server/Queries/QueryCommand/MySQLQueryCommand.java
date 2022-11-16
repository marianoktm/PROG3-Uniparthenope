package Server.Queries.QueryCommand;

import Server.Database.Database;
import Server.Database.MySQLDatabase;
import Server.Queries.QueriesHandler;

import java.sql.SQLException;

/**
 * An abstract COMMAND class that provides method signatures for CONCRETE COMMANDS and other boilerplate code.
 */
public abstract class MySQLQueryCommand {
    protected Database db;
    protected QueriesHandler queriesHandler;

    /**
     * Constructor that sets the database and the queries handler to use.
     */
    MySQLQueryCommand() {
        this.db = MySQLDatabase.getInstance();
        this.queriesHandler = QueriesHandler.getInstance();
    }

    /**
     * Executes the query.
     * @return the result of the query.
     * @throws SQLException if the query cannot be executed.
     */
    public abstract Object execute() throws SQLException;
}