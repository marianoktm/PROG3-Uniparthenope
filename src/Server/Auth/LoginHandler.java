package Server.Auth;

import Server.Queries.QueriesAdapter.QueryFetchAdapter;
import Server.Queries.QueriesAdapter.QueryOnerResultAdapter;
import Server.Queries.QueryClasses.GetUserDataQuery;
import Server.Queries.QueryClasses.MySQLQueryCommand;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.ArrayList;

public class LoginHandler {
    private static final LoginHandler instance = new LoginHandler();

    private LoginHandler() {
        if (instance != null)
            throw new InstantiationError("Creating this object is not allowed");
    }

    public static LoginHandler getInstance() {
        return instance;
    }

    public boolean login(String password, MySQLQueryCommand loginQueryPar) {
        ArrayList<ArrayList<String>> loginQueryArrayList = new ArrayList<>();

        QueryFetchAdapter query = new QueryFetchAdapter(loginQueryPar);
        loginQueryArrayList = query.execute();

        ArrayList<String> userLoginData = loginQueryArrayList.get(0);
        String givenPasswordHash = DigestUtils.sha256Hex(password);

        return userLoginData.get(2).equals(givenPasswordHash);
    }

}
