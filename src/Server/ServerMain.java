package Server;

import Server.Queries.QueriesAdapter.QueryUpdateAdapter;
import Server.Queries.QueryClasses.MySQLQueryCommand;
import Server.Queries.QueryClasses.SubmitTweet;
import Server.ServerClass.TwitterServer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain {
    public static void main(String[] args) {
        TwitterServerUtils.configHelper(args);

        TwitterServer server = TwitterServer.getInstance();
        server.start();
    }
}
