package Client.JavaFXGUI.Controllers;

import Client.Misc.Tweet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

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
    private ListView tweetList;

    private ObservableList<Tweet> items = FXCollections.observableArrayList();

    @FXML
    private Button updateTweetsBtn;

    @FXML
    void followSomeoneBtnClick(ActionEvent event) {

    }

    @FXML
    void logOutBtnClick(ActionEvent event) {

    }

    @FXML
    void submitTweetBtnClick(ActionEvent event) {

    }

    @FXML
    void updateTweetsBtnClick(ActionEvent event) {

    }

    // Test
    private ArrayList<Tweet> tweetCreator() {
        ArrayList<Tweet> tweetArrayList = new ArrayList<>();

        for (int i = 0; i < 15; i++) {
            tweetArrayList.add(new Tweet("username", Integer.toString(i), "MESSAGE!!!!", "20 oct bla bla"));
        }

        return tweetArrayList;
    }
}
