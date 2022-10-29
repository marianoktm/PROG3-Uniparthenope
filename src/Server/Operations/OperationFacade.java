package Server.Operations;

import Server.Operations.OpChain.LoginHandler;
import Server.Operations.OpChain.OperationChain;
import Server.Operations.OpChain.RegisterHandler;
import Server.Operations.OpChain.UserFollowHandler;
import Shared.ErrorHandling.Exceptions.InvalidTwitterOpException;
import Shared.ErrorHandling.Exceptions.SessionException;
import Shared.Packet.Packet;

import java.net.Socket;
import java.sql.SQLException;

public class OperationFacade {
    private static final OperationFacade instance = new OperationFacade();
    private final OperationChain chain = initializeChain();

    private OperationFacade() {
        if (instance != null) throw new InstantiationError("Creating this object is not allowed.");
    }

    public static OperationFacade getInstance() {
        return instance;
    }

    private OperationChain initializeChain() {
        // Anonymous class for chain head
        class ChainHead extends OperationChain {
            @Override
            public Packet perform(Socket socket, Packet packet) throws SessionException, SQLException, InvalidTwitterOpException {return next.perform(socket, packet); }
        }

        // Chain head initialization
        OperationChain chain = new ChainHead();

        // Chain building

        // Register
        OperationChain register = new RegisterHandler();
        chain.setNextChain(register);

        // User Login
        OperationChain userLogin = new LoginHandler();
        register.setNextChain(userLogin);

        // User Follow
        OperationChain userFollow = new UserFollowHandler();
        userLogin.setNextChain(userFollow);


        // Return chain head
        return chain;
    }

    public Packet fulfillRequest(Socket socket, Packet packet) throws SessionException, SQLException, InvalidTwitterOpException {
        return chain.perform(socket, packet);
    }
}

