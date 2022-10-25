package Server.Auth;

public abstract class LoginCommand {
    protected LoginHandler loginHandler;

    LoginCommand() {
        loginHandler = LoginHandler.getInstance();
    }

    public abstract boolean execute();
}
