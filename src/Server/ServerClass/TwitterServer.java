package Server.ServerClass;

import java.net.ServerSocket;

/**
 *
 */
public class TwitterServer {
    private static final TwitterServer instance = new TwitterServer();
    private ServerSocket serverSocket;
    private int port;

    /**
     *
     */
    private TwitterServer() {
        if (instance != null) throw new InstantiationError("Creating this object is not allowed");
    }

    /**
     * @return
     */
    public static TwitterServer getInstance() {
        return instance;
    }

    /**
     * @param port
     */
    public void config(int port) {
        this.port = port;
    }

    /**
     *
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
     *
     */
    public void stop() {
        try { serverSocket.close(); }
        catch (Exception e) { e.printStackTrace(); }
    }
}
