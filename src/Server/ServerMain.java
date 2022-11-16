package Server;

import Server.ServerClass.TwitterServer;
import Server.Utils.TwitterServerUtils;

/**
 * The main class of Twitter 2 Server Application. Runs a configuration of all the needed objects and runs the server.
 */
public class ServerMain {
    /**
     * Main method of ServerMain.
     * @param args a custom path to server_config.properties.
     */
    public static void main(String[] args) {
        TwitterServerUtils.configHelper(args);

        TwitterServer server = TwitterServer.getInstance();
        server.start();
    }
}
