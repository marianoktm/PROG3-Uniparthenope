package Server;

import Server.ServerClass.TwitterServer;

public class ServerMain {
    public static void main(String[] args) {
        TwitterServerUtils.configHelper(args);

        TwitterServer server = TwitterServer.getInstance();
        server.start();
    }
}
