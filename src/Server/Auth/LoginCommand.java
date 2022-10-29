package Server.Auth;

public abstract class LoginCommand {
    protected LoginHandler1 loginHandler1;

    LoginCommand() {
        loginHandler1 = LoginHandler1.getInstance();
    }

    public abstract boolean execute();
}
