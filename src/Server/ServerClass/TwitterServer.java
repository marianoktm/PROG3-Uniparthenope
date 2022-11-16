package Server.ServerClass;

import java.net.ServerSocket;

/**
 * The actual SINGLETON server class of Twitter 2 Server application. Provides method to config, start and stop the server.
 */
public class TwitterServer {
    private static final TwitterServer instance = new TwitterServer();
    private ServerSocket serverSocket;
    private int port;

    private TwitterServer() {
        if (instance != null) throw new InstantiationError("Creating this object is not allowed");
    }

    /**
     * Returns the singleton instance of the server.
     * @return a singleton instance of this class.
     */
    public static TwitterServer getInstance() {
        return instance;
    }

    /**
     * Configs the server port.
     * @param port the port of the server.
     */
    public void config(int port) {
        this.port = port;
    }

    /**
     * Starts the server.
     */
    public void start() {
        try {
            serverSocket = new ServerSocket(port);
            while (true) new TwitterServerHandler(serverSocket.accept()).start();
        }
        catch (Exception e) { e.printStackTrace(); }
        finally { stop(); }
    }

    /**
     * Stops the server.
     */
    public void stop() {
        try { serverSocket.close(); }
        catch (Exception e) { e.printStackTrace(); }
    }
}
