package Server.Queries.QueriesAdapter;

import Server.Queries.QueryClasses.MySQLQueryCommand;

import java.util.ArrayList;

public class QueryOnerResultAdapter {
    private final QueryFetchAdapter fetch;

    public QueryOnerResultAdapter(MySQLQueryCommand command) {
        this.fetch = new QueryFetchAdapter(command);
    }

    public String execute() {
        ArrayList<ArrayList<String>> out = new ArrayList<>();
        out = fetch.execute();

        return out.get(0).get(0);
    }
}
