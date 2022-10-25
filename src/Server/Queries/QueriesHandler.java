package Server.Queries;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class QueriesHandler {
    private static final QueriesHandler instance = new QueriesHandler();
    private String queryDirectory;

    private QueriesHandler() {
        if (instance != null) throw new InstantiationError("Creating this object is not allowed.");
    }

    public static QueriesHandler getInstance() {
        return instance;
    }

    public void config(String dir) {
        this.queryDirectory = dir;
    }

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
