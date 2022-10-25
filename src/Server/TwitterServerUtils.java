package Server;

import Server.Database.Database;
import Server.Database.MySQLDatabase;
import Server.Queries.QueriesHandler;
import Server.ServerClass.TwitterServer;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

public class TwitterServerUtils {
    public static void print2DArrayList(ArrayList<ArrayList<String>> list) {
        for (ArrayList<String> row : list) {
            for (String element : row) {
                System.out.println(element);
            }
            System.out.println("==========================");
        }
    }

    public static void configHelper(String[] args) {
        // Load config file
        String configFilePath;

        // Set config path
        if (args.length == 0)
            configFilePath = "src/server/server_config.properties";
        else
            configFilePath = args[0];

        System.out.println("Config path set to: " + configFilePath);

        // Load properties
        try {
            FileInputStream propsInput = new FileInputStream(configFilePath);

            Properties prop = new Properties();
            prop.load(propsInput);

            // Db configs
            Database db = MySQLDatabase.getInstance();
            db.config(prop.getProperty("DB_URL"), prop.getProperty("DB_USER"), prop.getProperty("DB_PASS"));

            // Queries config
            QueriesHandler queriesHandler = QueriesHandler.getInstance();
            queriesHandler.config(prop.getProperty("SQL_SRC"));

            // Server config
            TwitterServer twitterServer = TwitterServer.getInstance();
            twitterServer.config(Integer.parseInt(prop.getProperty("SRV_PORT")));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
