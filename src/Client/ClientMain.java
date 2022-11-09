package Client;

import Client.ClientClass.TwitterClient;
import Client.Misc.ClientConfig;
import Client.Misc.TwitterClientUtils;
import Shared.ErrorHandling.ErrorCode;
import Shared.Packet.Packet;
import Shared.Packet.RequestCode;
import Shared.Packet.Session;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ClientMain {
    ClientMain(String[] args) {
        ClientConfig config = new ClientConfig(args);
        TwitterClient client = new TwitterClient(config.getServerRecord());
    }

    public static void main(String[] args) throws IOException {


        // if (test(client)) System.out.println("EVERYTHING WORKS!");
    }


    private static boolean test(TwitterClient client) throws IOException {
        client.connect();


        String username = "peppe";
        String email = "register@gmail.com";
        String password = "123456789";
        Session dummy = new Session(null, null, null);

        /*
        // Register
        List<String> registerData = new ArrayList<>();
        registerData.add(username);
        registerData.add(email);
        registerData.add(password);

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
        */



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


        /*
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
        */


        // Tweet
        /*
        List<String> tweetData = new ArrayList<>();
        tweetData.add(null);
        tweetData.add("Prova null!");

        Packet nullTweet = new Packet(RequestCode.SUBMIT_TWEET, session, tweetData, null, ErrorCode.NONE);

        client.sendPacket(nullTweet);

        Packet nullTweetResult = client.getPacket();

        if (nullTweetResult.isSuccessful) {
            System.out.println("Tweet submitted!");
        }
        else {
            System.err.println("Null Tweet error: " + nullTweetResult.errorCode);
            client.disconnect();
            return false;
        }
         */

        // Fetch Followed Tweets
        Packet fetchTw = new Packet(RequestCode.FETCH_TWEETS, session, null, null, ErrorCode.NONE);
        client.sendPacket(fetchTw);

        Packet fetchTwResult = client.getPacket();

        if (fetchTwResult.isSuccessful) {
            System.out.println("TWEETS:");

            TwitterClientUtils.print2DArrayList((ArrayList<ArrayList<String>>) fetchTwResult.data);
        }
        else {
            System.err.println("Fetch tweet error: " + fetchTwResult.errorCode);
            client.disconnect();
            return false;
        }

        client.disconnect();
        return true;
    }
}
