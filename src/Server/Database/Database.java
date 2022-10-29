package Server.Database;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface Database {
    // Configuration method
    void                            config(String url, String user, String pass);

    // Parametrized queries
    Object                          execQuery(String queryStr, List<Object> queryParameters) throws SQLException;
    int                             execUpdate(String queryStr, List<Object> queryParameters) throws SQLException;

    // Simple queries
    Object                          execQuery(String query) throws SQLException;
    int                             execUpdate(String queryStr) throws SQLException;
}
