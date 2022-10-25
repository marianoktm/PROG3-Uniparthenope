package Server.Queries.QueryClasses;

import java.util.ArrayList;
import java.util.List;

public class GetAdminDataQuery extends MySQLQueryCommand {
    private final String admin_id;

    public GetAdminDataQuery(String admin_id) {
        this.admin_id = admin_id;
    }

    @Override
    public Object execute() {
        String query = queriesHandler.getQuery("get_admin_data.sql");
        List<Object> queryParameters = new ArrayList<>();

        queryParameters.add(admin_id);

        return db.execQuery(query, queryParameters);
    }
}
