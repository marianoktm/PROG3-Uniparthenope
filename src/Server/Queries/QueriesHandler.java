package Server.Queries;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * A SINGLETON utils class that provides code to get queries strings.
 */
public class QueriesHandler {
    private static final QueriesHandler instance = new QueriesHandler();
    private String queryDirectory;

    private QueriesHandler() {
        if (instance != null) throw new InstantiationError("Creating this object is not allowed.");
    }

    /**
     * Returns the handler instance.
     * @return a singleton instance of the class.
     */
    public static QueriesHandler getInstance() {
        return instance;
    }

    /**
     * Sets the directory where the queries can be found.
     * @param dir the queries directory
     */
    public void config(String dir) {
        this.queryDirectory = dir;
    }

    /**
     * Searches for a query file (.sql) and returns its text.
     * @param filename the filename
     * @return the query String
     */
    public String getQuery(String filename) {
        String query = "";

        try {
            File queryFile = new File(queryDirectory + filename);
            Scanner fileReader = new Scanner(queryFile);

            while (fileReader.hasNextLine())
                query = fileReader.nextLine();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return query;
    }
}
