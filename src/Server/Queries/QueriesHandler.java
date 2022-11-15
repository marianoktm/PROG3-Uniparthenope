package Server.Queries;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 */
public class QueriesHandler {
    private static final QueriesHandler instance = new QueriesHandler();
    private String queryDirectory;

    /**
     *
     */
    private QueriesHandler() {
        if (instance != null) throw new InstantiationError("Creating this object is not allowed.");
    }

    /**
     * @return
     */
    public static QueriesHandler getInstance() {
        return instance;
    }

    /**
     * @param dir
     */
    public void config(String dir) {
        this.queryDirectory = dir;
    }

    /**
     * @param filename
     * @return
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
