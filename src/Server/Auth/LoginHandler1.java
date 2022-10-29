package Server.Auth;

import Server.Queries.QueryAdapter.QueryFetchAdapter;
import Server.Queries.QueryCommand.MySQLQueryCommand;
import org.apache.commons.codec.digest.DigestUtils;

import java.sql.SQLException;
import java.util.ArrayList;

public class LoginHandler1 {
    private static final LoginHandler1 instance = new LoginHandler1();

    private LoginHandler1() {
        if (instance != null)
            throw new InstantiationError("Creating this object is not allowed");
    }

    public static LoginHandler1 getInstance() {
        return instance;
    }

    public boolean login(String password, MySQLQueryCommand loginQueryPar) throws SQLException {
        ArrayList<ArrayList<String>> loginQueryArrayList;

        QueryFetchAdapter query = new QueryFetchAdapter(loginQueryPar);
        loginQueryArrayList = query.execute();

        ArrayList<String> userLoginData = loginQueryArrayList.get(0);
        String givenPasswordHash = DigestUtils.sha256Hex(password);

        return userLoginData.get(2).equals(givenPasswordHash);
    }

}
