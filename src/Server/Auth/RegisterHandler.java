package Server.Auth;

import Server.Database.MySQLDatabase;
import Server.Queries.QueriesAdapter.QueryUpdateAdapter;
import Server.Queries.QueryClasses.MySQLQueryCommand;
import Server.Queries.QueryClasses.UserRegisterQuery;
import org.apache.commons.codec.digest.DigestUtils;

public class RegisterHandler {
    private static RegisterHandler instance;

    private RegisterHandler() {
        if (instance != null) throw new InstantiationError("Creating this object is not allowed!");
    }

    public static RegisterHandler getInstance() {
        return instance;
    }

    public boolean register(String username, String email, String password) {
        String passwordHash = DigestUtils.sha256Hex(password);

        MySQLQueryCommand query = new UserRegisterQuery(username, email, passwordHash);
        QueryUpdateAdapter registerQuery = new QueryUpdateAdapter(query);

        return registerQuery.execute() > 0;
    }
}
