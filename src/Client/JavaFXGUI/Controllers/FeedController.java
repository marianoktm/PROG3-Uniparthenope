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

/**
 * A general controller for the Feed fxml. It can be extended by subclasses to implement operations on buttons.
 */
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

    /**
     * Executes the event related to btn1.
     * @param event the javafx event arisen by clicking btn1.
     */
    @FXML
    protected void btn1Click(ActionEvent event) {
        System.out.println("btn1 Clicked");
    }

    /**
     * Executes the event related to btn2.
     * @param event the javafx event arisen by clicking btn2.
     * @throws IOException if packets can't be read.
     */
    @FXML
    protected void btn2Click(ActionEvent event) throws IOException {
        System.out.println("btn2 Clicked");
    }

    /**
     * Executes the event related to btn3.
     * @param event the javafx event arisen by clicking btn3.
     * @throws IOException if packets can't be read.
     */
    @FXML
    protected void btn3Click(ActionEvent event) throws IOException {
        System.out.println("btn3 Clicked");
    }

    /**
     * Executes the event related to updateTweetsBtn.
     * @param event the javafx event arisen by clicking updateTweetsBtn.
     * @throws IOException if packets can't be read.
     * @throws FetchException if tweets can't be fetched.
     * @throws EmptyFieldException if hashtag field is empty.
     */
    @FXML
    protected void updateTweetsBtnClick(ActionEvent event) throws IOException, FetchException, EmptyFieldException {
        System.out.println("updateTweetsBtn Clicked");
    }

    /**
     * Converts the list of lists provided by the server in an array of tweets.
     * @param data the data previously read on the socket after a tweet fetch request.
     * @return an array of Tweet objects.
     */
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

    /**
     * Prints tweets on screen into the Scrollable VBox used to show them.
     * @param fetchTweetsResult the packet read on the socket after a tweet fetch request.
     * @throws IOException if there's an error loading Tweet.fxml
     */
    protected void printTweets(Packet fetchTweetsResult) throws IOException {
        TwitterClientUtils.print2DArrayList((ArrayList<ArrayList<String>>) fetchTweetsResult.data);

        ArrayList<Tweet> to_add = listOfListsToTweetArray((ArrayList<ArrayList<String>>) fetchTweetsResult.data);

        URL tweetFXMLUrl = getClass().getResource(StageFacade.getFXMLCompletePath("Tweet"));

        tweetVBox.getChildren().clear();

        for (Tweet tweet : to_add) {
            FXMLLoader tweetLoader = new FXMLLoader(tweetFXMLUrl);
            Parent tweetAnchor = tweetLoader.load();

            tweetVBox.getChildren().add(tweetAnchor);

            TweetController tweetController = tweetLoader.getController();
            tweetController.setFields(tweet.username, tweet.hashtag, tweet.message, tweet.datetime);
        }
    }

    /**
     * Initializes controller's fields.
     */
    public void init() {
        System.out.println("Init launched.");
    }
}
