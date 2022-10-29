package Server.Queries.QueryAdapter;

import Server.Queries.QueryCommand.MySQLQueryCommand;

import java.sql.SQLException;

public class QueryUpdateAdapter {
    private final MySQLQueryCommand command;

    public QueryUpdateAdapter(MySQLQueryCommand command) {
        this.command = command;
    }

    public int execute() throws SQLException {
        return (int) command.execute();
    }
}
