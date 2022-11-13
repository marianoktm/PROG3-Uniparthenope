package Client.JavaFXGUI.Controllers;

import Client.JavaFXGUI.Classes.StageFacade;
import Client.JavaFXGUI.GUIObjects.Tweet;
import Client.Misc.TwitterClientUtils;
import Shared.ErrorHandling.Exceptions.EmptyFieldException;
import Shared.ErrorHandling.Exceptions.FetchException;
import Shared.Packet.Packet;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;


public class FeedController extends ConnectedUIController {
    @FXML
    protected Button btn1;

    @FXML
    protected Button btn2;

    @FXML
    protected Button btn3;

    @FXML
    protected TextField hashtagField;

    @FXML
    protected Label loggedAsLabel;

    @FXML
    protected Label title;

    @FXML
    protected VBox tweetVBox;

    @FXML
    protected ScrollPane tweetsScrollPane;

    @FXML
    protected Button updateTweetsBtn;

    public FeedController() {
        System.out.println("Feed Controller instantiated.");
    }

    @FXML
    protected void btn1Click(ActionEvent event) {
        System.out.println("btn1 Clicked");
    }

    @FXML
    protected void btn2Click(ActionEvent event) throws IOException {
        System.out.println("btn2 Clicked");
    }

    @FXML
    protected void btn3Click(ActionEvent event) throws IOException, FetchException {
        System.out.println("btn3 Clicked");
    }

    @FXML
    protected void updateTweetsBtnClick(ActionEvent event) throws IOException, FetchException, EmptyFieldException {
        System.out.println("updateTweetsBtn Clicked");
    }

    protected static ArrayList<Tweet> listOfListsToTweetArray(ArrayList<ArrayList<String>> data) {
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

    protected void printTweets(Packet fetchTweetsResult) throws IOException {
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

    public void init() {
        System.out.println("Init launched.");
    }
}
