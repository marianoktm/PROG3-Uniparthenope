package Server.Database;

import java.util.ArrayList;
import java.util.List;

public interface Database {
    // Configuration method
    void                            config(String url, String user, String pass);

    // Parametrized queries
    Object                          execQuery(String queryStr, List<Object> queryParameters);
    int                             execUpdate(String queryStr, List<Object> queryParameters);

    // Simple queries
    Object                          execQuery(String query);
    int                             execUpdate(String queryStr);
}
