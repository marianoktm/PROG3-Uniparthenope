package Server;

import Server.ServerClass.TwitterServer;
import Server.Utils.TwitterServerUtils;

public class ServerMain {
    public static void main(String[] args) {
        TwitterServerUtils.configHelper(args);

        TwitterServer server = TwitterServer.getInstance();
        server.start();
    }
}
