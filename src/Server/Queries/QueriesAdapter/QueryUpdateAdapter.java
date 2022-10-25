package Server.Queries.QueriesAdapter;

import Server.Queries.QueryClasses.MySQLQueryCommand;

public class QueryUpdateAdapter {
    private final MySQLQueryCommand command;

    public QueryUpdateAdapter(MySQLQueryCommand command) {
        this.command = command;
    }

    public int execute() {
        return (int) command.execute();
    }
}
