package Server.Queries.QueryCommand;

import Server.Database.Database;
import Server.Database.MySQLDatabase;
import Server.Queries.QueriesHandler;

import java.sql.SQLException;

public abstract class MySQLQueryCommand {
    protected Database db;
    protected QueriesHandler queriesHandler;

    MySQLQueryCommand() {
        this.db = MySQLDatabase.getInstance();
        this.queriesHandler = QueriesHandler.getInstance();
    }

    public abstract Object execute() throws SQLException;
}
