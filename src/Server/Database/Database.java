package Server.Database;

import java.sql.SQLException;
import java.util.List;

/**
 *
 */
public interface Database {
    /**
     * @param url
     * @param user
     * @param pass
     */
    // Configuration method
    void config(String url, String user, String pass);

    /**
     * @param queryStr
     * @param queryParameters
     * @return
     * @throws SQLException
     */
    // Parametrized queries
    Object execQuery(String queryStr, List<Object> queryParameters) throws SQLException;

    /**
     * @param queryStr
     * @param queryParameters
     * @return
     * @throws SQLException
     */
    int execUpdate(String queryStr, List<Object> queryParameters) throws SQLException;

    /**
     * @param query
     * @return
     * @throws SQLException
     */
    // Simple queries
    Object execQuery(String query) throws SQLException;

    /**
     * @param queryStr
     * @return
     * @throws SQLException
     */
    int execUpdate(String queryStr) throws SQLException;
}
