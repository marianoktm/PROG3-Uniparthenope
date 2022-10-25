package Server.Queries.QueriesAdapter;

import Server.Queries.QueryClasses.MySQLQueryCommand;

import java.util.ArrayList;

public class QueryFetchAdapter {
    private final MySQLQueryCommand command;

    public QueryFetchAdapter(MySQLQueryCommand command) {
        this.command = command;
    }

    public ArrayList<ArrayList<String>> execute() {
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
