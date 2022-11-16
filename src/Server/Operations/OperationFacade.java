package Server.Operations;

import Server.Operations.OpChain.*;
import Shared.ErrorHandling.Exceptions.BanException;
import Shared.ErrorHandling.Exceptions.InvalidTwitterOpException;
import Shared.ErrorHandling.Exceptions.SessionException;
import Shared.Packet.Packet;

import java.net.Socket;
import java.sql.SQLException;

/**
 * A SINGLETON FACADE class for Operations that will be executed on the server. Initializes the OperationChain setting all COR Handlers and provides a method to run through the chain to fulfil a request.
 */
public class OperationFacade {
    private static final OperationFacade instance = new OperationFacade();
    private final OperationChain chain = initializeChain();

    private OperationFacade() {
        if (instance != null) throw new InstantiationError("Creating this object is not allowed.");
    }

    /**
     * @return the singleton instance of the class.
     */
    public static OperationFacade getInstance() {
        return instance;
    }

    /**
     * @return
     */
    private OperationChain initializeChain() {
        // Anonymous class for chain head
        class ChainHead extends OperationChain {
            @Override
            public Packet perform(Socket socket, Packet packet) throws SessionException, SQLException, InvalidTwitterOpException, BanException {return next.perform(socket, packet); }
        }

        // Chain head initialization
        OperationChain chain = new ChainHead();

        // Chain building

        // User Register
        OperationChain register = new RegisterHandler();
        chain.setNextChain(register);

        // User Login
        OperationChain userLogin = new LoginHandler();
        register.setNextChain(userLogin);

        // User Follow
        OperationChain userFollow = new UserFollowHandler();
        userLogin.setNextChain(userFollow);

        // Submit Tweet
        OperationChain submitTweet = new SubmitTweetHandler();
        userFollow.setNextChain(submitTweet);

        // User Fetch Followed Tweets
        OperationChain fetchTweetHandler = new FetchTweetHandler();
        submitTweet.setNextChain(fetchTweetHandler);

        // User Logout
        OperationChain userLogoutHandler = new UserLogoutHandler();
        fetchTweetHandler.setNextChain(userLogoutHandler);

        // Admin Login
        OperationChain adminLoginHandler = new AdminLoginHandler();
        userLogoutHandler.setNextChain(adminLoginHandler);

        // Admin Delete User
        OperationChain adminDeleteUserHandler = new AdminDeleteUserHandler();
        adminLoginHandler.setNextChain(adminDeleteUserHandler);

        // Fetch Tweet By Hashtag
        OperationChain fetchTweetByHashtagHandler = new FetchTweetByHashtagHandler();
        adminDeleteUserHandler.setNextChain(fetchTweetByHashtagHandler);

        // Admin Logout
        OperationChain adminLogoutHandler = new AdminLogoutHandler();
        fetchTweetByHashtagHandler.setNextChain(adminLogoutHandler);

        // Return chain head
        return chain;
    }

    /**
     * Fulfils a request in the operation chain.
     * @param socket the socket where the packet will be read or sent.
     * @param packet the packet containing all the data needed by operations to be performed.
     * @return a response packet.
     * @throws SessionException if the session is invalid.
     * @throws SQLException if a query can't be executed.
     * @throws InvalidTwitterOpException if the request code in the packet is invalid.
     * @throws BanException if a user tries to perform an operation while banned.
     */
    public Packet fulfillRequest(Socket socket, Packet packet) throws SessionException, SQLException, InvalidTwitterOpException, BanException {
        return chain.perform(socket, packet);
    }
}

