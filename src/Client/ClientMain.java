package Client;

import Client.ClientClass.TwitterClient;
import Client.Misc.ClientConfig;
import Shared.ErrorHandling.ErrorCode;
import Shared.Packet.Packet;
import Shared.Packet.RequestCode;
import Shared.Packet.Session;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ClientMain {
    public static void main(String[] args) throws IOException {
        ClientConfig config = new ClientConfig(args);
        TwitterClient client = new TwitterClient(config.getServerRecord());

        if (test(client)) System.out.println("EVERYTHING WORKS!");
    }

    private static boolean test(TwitterClient client) throws IOException {
        client.connect();
        // Register
        String username = "registerTest" + "7";
        String email = "register@gmail.com";
        String password = "password";

        List<String> registerData = new ArrayList<>();
        registerData.add(username);
        registerData.add(email);
        registerData.add(password);
        Session dummy = new Session(null, null, null);

        Packet registerPacket = new Packet(RequestCode.USER_REGISTER, dummy, registerData , null, ErrorCode.NONE);

        client.sendPacket(registerPacket);

        Packet registerResult = client.getPacket();

        if (registerResult.isSuccessful) {
            System.out.println("Registration completed!");
        }
        else {
            System.err.println("Registration error :" + registerResult.errorCode);
            client.disconnect();
            return false;
        }


        // Login
        List<String> loginData = new ArrayList<>();
        loginData.add(username);
        loginData.add(password);

        Packet loginPacket = new Packet(RequestCode.USER_LOGIN, dummy, loginData, null, ErrorCode.NONE);

        client.sendPacket(loginPacket);

        Packet loginResult = client.getPacket();

        Session session;
        if (loginResult.isSuccessful) {
            System.out.println("Login success!");
            session = new Session(loginResult.session);
        }
        else {
            System.err.println("Login error :" + loginResult.errorCode);
            client.disconnect();
            return false;
        }


        // Follow
        List<String> followData = new ArrayList<>();
        followData.add(username);
        followData.add("peppe");

        Packet followPacket = new Packet(RequestCode.USER_FOLLOW, session, followData, null, ErrorCode.NONE);

        client.sendPacket(followPacket);

        Packet followResult = client.getPacket();

        if (followResult.isSuccessful) {
            System.out.println("Follow success!");
        }
        else {
            System.err.println("Follow error: " + followResult.errorCode);
            client.disconnect();
            return false;
        }

        client.disconnect();
        return true;
    }
}
