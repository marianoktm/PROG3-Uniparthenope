package Client.JavaFXGUI.Controllers;

import Client.JavaFXGUI.Classes.Home;
import Client.JavaFXGUI.Classes.StageFacade;
import Client.JavaFXGUI.GUIObjects.Tweet;
import Client.Misc.TwitterClientUtils;
import Shared.ErrorHandling.ErrorCode;
import Shared.ErrorHandling.Exceptions.FetchException;
import Shared.Packet.Packet;
import Shared.Packet.RequestCode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class FeedController {

    @FXML
    private Button followSomeoneBtn;

    @FXML
    private Button logOutBtn;

    @FXML
    private Label loggedAsLabel;

    @FXML
    private Button submitTweetBtn;

    @FXML
    private VBox tweetVBox;

    @FXML
    private ScrollPane tweetsScrollPane;

    @FXML
    private Button updateTweetsBtn;

    @FXML
    void followSomeoneBtnClick(ActionEvent event) {
        System.out.println("Follow Someone Button Clicked");

        try { new StageFacade("Follow", "Follow").show(); }
        catch (IOException e) { e.printStackTrace();}
    }

    @FXML
    void logOutBtnClick(ActionEvent event) {
        System.out.println("Log Out Button Clicked.");
    }

    @FXML
    void submitTweetBtnClick(ActionEvent event) {
        System.out.println("Submit Tweet Button Clicked");

        try { new StageFacade("SubmitTweet", "Submit Tweet").show(); }
        catch (IOException e) { e.printStackTrace();}
    }

    @FXML
    void updateTweetsBtnClick(ActionEvent event) throws IOException, FetchException {
        System.out.println("Update Tweets Button Clicked.");

        Packet fetchTweets = new Packet(RequestCode.FETCH_TWEETS, Home.session, null, null, ErrorCode.NONE);

        Home.client.connect();
        Home.client.sendPacket(fetchTweets);

        Packet fetchTweetsResult = Home.client.getPacket();
        Home.client.disconnect();

        if (fetchTweetsResult.isSuccessful) {
            TwitterClientUtils.print2DArrayList((ArrayList<ArrayList<String>>) fetchTweetsResult.data);

            ArrayList<Tweet> to_add = listOfListsToTweetArray((ArrayList<ArrayList<String>>) fetchTweetsResult.data);

            URL tweetFXMLUrl = getClass().getResource(StageFacade.getFXMLCompletePath("Tweet"));

            for (Tweet tweet : to_add) {
                FXMLLoader tweetLoader = new FXMLLoader(tweetFXMLUrl);
                Parent tweetAnchor = tweetLoader.load();

                tweetVBox.getChildren().add(tweetAnchor);

                TweetController tweetController = tweetLoader.getController();
                tweetController.setFields(tweet.username, tweet.hashtag, tweet.message, tweet.datetime);
            }

        }
        else {
            switch (fetchTweetsResult.errorCode) {
                case FETCH_TWEETS_ERROR -> throw new FetchException("An error occurred while fetching latest tweets!");
                default -> throw new FetchException("An unknown error occurred while fetching latest tweets.");
            }
        }
    }

    private static ArrayList<Tweet> listOfListsToTweetArray(ArrayList<ArrayList<String>> data) {
        ArrayList<Tweet> out = new ArrayList<>();

        // each row of arraylist data is a tweet: (0) username - (1) hashtag - (2) message - (3) postdate
        for (ArrayList<String> row : data) {
            Tweet to_add = new Tweet(row.get(0), row.get(1), row.get(2), row.get(3));
            System.out.println("Added to tweetlist:");
            System.out.println(to_add.toString());
            out.add(to_add);
        }

        return out;
    }
}
