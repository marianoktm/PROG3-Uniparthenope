package Server.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class MySQLDatabase implements Database {
    private static final MySQLDatabase instance = new MySQLDatabase();

    private String connectionUrl;
    private String dbUser;
    private String dbPass;

    private String dbAdmin = null;
    private String dbAdminPass = null;

    private MySQLDatabase() {
        if (instance != null) throw new InstantiationError("Creating this object is not allowed.");
    }

    public static MySQLDatabase getInstance() {
        return instance;
    }

    /* PRIVATE UTILITIES */

    private Connection createConnection() {
        Connection connection = null;

        // Get connection from driver manager
        if (connectionUrl == null) return null;

        try {
            connection = DriverManager.getConnection(connectionUrl, dbUser, dbPass);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }

    private PreparedStatement prepareStatementHelper(Connection connection, String queryStr, List<Object> queryParameters) {
        PreparedStatement statement = null;

        try {
            // Initialize a prepared statement
            statement = connection.prepareStatement(queryStr);

            // Inserting all query parameters into the query ("?" placeholders will be replaced)
            if (queryParameters != null) {
                int placeholderIndex = 1;
                for (Object to_insert : queryParameters) {
                    if (to_insert instanceof Integer)
                        statement.setInt(placeholderIndex, (Integer) to_insert);
                    else if (to_insert instanceof String)
                        statement.setString(placeholderIndex, (String) to_insert);
                    placeholderIndex++;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try { System.out.println(statement.toString()); }
        catch (NullPointerException e) { e.printStackTrace(); }

        return statement;
    }

    private ArrayList<ArrayList<String>> convertResultSet(ResultSet resultSet) {
        // Saving query results in a data structure (result set is lost after connection closed)
        ArrayList<ArrayList<String>> rowList = new ArrayList<>();

        try {
            ResultSetMetaData resultSetMeta = resultSet.getMetaData();

            // Saving query results in a data structure (result set is lost after connection closed)
            while (resultSet.next()) {
                ArrayList<String> colList = new ArrayList<>();

                for (int i = 1; i <= resultSetMeta.getColumnCount(); i++) {
                    Object to_add = resultSet.getString(i);
                    // ***[DEBUG]*** System.out.println("[DEBUG]: Extracted " + i + ": " + to_add.toString());
                    colList.add(to_add.toString());
                }
                rowList.add(colList);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return rowList;
    }

    public void config(String url, String user, String pass) {
        System.out.println("Url: " + url);
        System.out.println("User: " + user);
        System.out.println("Pass: " + pass);

        this.connectionUrl = url;
        this.dbUser = user;
        this.dbPass = pass;
    }

    /* QUERY */

    @Override
    public Object execQuery(String queryStr, List<Object> queryParameters) {
        // Declaring JDBC resources
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        // Query result (function output)
        ArrayList<ArrayList<String>> out = new ArrayList<ArrayList<String>>();

        try {
            // Opening DB connection and initialize a prepared statement
            connection = createConnection();
            statement = prepareStatementHelper(connection, queryStr, queryParameters);

            // Executing the query and retrieving its metadata
            resultSet = statement.executeQuery();

            out = convertResultSet(resultSet);
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            // Releasing all JDBC resources
            try {
                if (connection != null) connection.close();
                if (statement != null) statement.close();
                if (resultSet != null) resultSet.close();
            } catch (SQLException exc) {
                exc.printStackTrace();
            }
        }

        return out;
    }

    @Override
    public Object execQuery(String queryStr) {
        return execQuery(queryStr, null);
    }

    /* UPDATE (insert, delete, update) */

    @Override
    public int execUpdate(String queryStr, List<Object> queryParameters) {
        // Declaring JDBC resources
        Connection connection = null;
        PreparedStatement statement = null;
        int updated_no = 0;

        try {
            // Opening DB connection and initialize a prepared statement
            connection = createConnection();
            statement = prepareStatementHelper(connection, queryStr, queryParameters);

            // Executing the insert query
            updated_no = statement.executeUpdate();
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            // Releasing all JDBC resources
            try {
                if (connection != null) connection.close();
                if (statement != null) statement.close();
            } catch (SQLException exc) {
                exc.printStackTrace();
            }
        }
        return updated_no;
    }

    @Override
    public int execUpdate(String queryStr) {
        return execUpdate(queryStr, null);
    }
}
