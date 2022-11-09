package Client.JavaFXGUI.Controllers;

import Client.JavaFXGUI.Classes.StageFacade;
import Client.Misc.Tweet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.io.IOException;

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
    private ListView tweetList;

    private ObservableList<Tweet> items = FXCollections.observableArrayList();

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
    void updateTweetsBtnClick(ActionEvent event) {
        System.out.println("Update Tweets Button Clicked.");
    }
}
