package Server.Database;

import java.sql.SQLException;
import java.util.List;

/**
 * Database interface for databases that connects to the server. Provides basic database methods needed to handle queries.
 */
public interface Database {
    /**
     * Configs the class with database url, user and user password to handle connection.
     * @param url
     * @param user
     * @param pass
     */
    // Configuration method
    void config(String url, String user, String pass);

    /**
     * Executes a query (SELECT) to the database through prepared statement with parameters.
     * @param queryStr the query String to be executed with placeholders (?s) for parameters.
     * @param queryParameters a list of parameters.
     * @return a list of lists of results. Each row is a tuple. Each col is a tuple's col.
     * @throws SQLException if the query cannot be executed.
     */
    Object execQuery(String queryStr, List<Object> queryParameters) throws SQLException;

    /**
     * Executes an update query (INSERT, UPDATE, DELETE) to the database through prepared statement with parameters.
     * @param queryStr the query String to be executed with placeholders (?s) for parameters.
     * @param queryParameters a list of parameters
     * @return the number of rows updated
     * @throws SQLException if the query cannot be executed.
     */
    int execUpdate(String queryStr, List<Object> queryParameters) throws SQLException;

    /**
     * Executes a query (SELECT) to the database through prepared statement.
     * @param query the query String to be executed.
     * @return a list of lists of results. Each row is a tuple. Each col is a tuple's col.
     * @throws SQLException if the query cannot be executed.
     */
    Object execQuery(String query) throws SQLException;

    /**
     * Executes an update query (INSERT, UPDATE, DELETE) to the database through prepared statement.
     * @param queryStr the query String to be executed.
     * @return the number of rows updated
     * @throws SQLException if the query cannot be executed.
     */
    int execUpdate(String queryStr) throws SQLException;
}
