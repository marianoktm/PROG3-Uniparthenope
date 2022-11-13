package Client.JavaFXGUI.Controllers;

import Client.ClientClass.TwitterClientWrapper;
import Client.JavaFXGUI.Classes.PopUpWrapper;
import Client.JavaFXGUI.Classes.StageFacade;
import Shared.ErrorHandling.Exceptions.EmptyFieldException;
import Shared.ErrorHandling.Exceptions.FetchException;
import Shared.Packet.Packet;
import Shared.Packet.RequestCode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AdminFeedController extends FeedController {

    @FXML
    @Override
    // Delete User
    protected void btn1Click(ActionEvent event) {
        System.out.println("Delete user clicked.");
    }

    @FXML
    @Override
    // Log Out
    protected void btn2Click(ActionEvent event) throws IOException {
        System.out.println("Logout Button Clicked");

        sendSessionedPacket(RequestCode.ADMIN_LOGOUT, null);
        String message;

        if (getAndDisconnect().isSuccessful) message = "Logout success!";
        else message = "Session is either invalid or expired, or another error happened on logout. You may close the application.";

        PopUpWrapper.showStage("Home", "Home");
        PopUpWrapper.showDialog("Dialog", "Log Out", message);

        StageFacade.closeStageFromBtn(this.btn3);
    }

    @Override
    @FXML
    protected void updateTweetsBtnClick(ActionEvent event) throws IOException, FetchException, EmptyFieldException {
        System.out.println("Update Tweets Button Clicked.");

        List<String> packetData = new ArrayList<>();

        String hashtag = this.hashtagField.getText();
        if (hashtag.isEmpty()) throw new EmptyFieldException("Empty fields not allowed.");

        packetData.add(hashtag);

        sendSessionedPacket(RequestCode.FETCH_HASHTAG, packetData);
        Packet fetchTweetsResult = getAndDisconnect();

        if (fetchTweetsResult.isSuccessful) {
            printTweets(fetchTweetsResult);
        }
        else {
            switch (fetchTweetsResult.errorCode) {
                case FETCH_TWEETS_ERROR -> throw new FetchException("An error occurred while fetching latest tweets!");
                default -> throw new FetchException("An unknown error occurred while fetching latest tweets.");
            }
        }
    }

    @Override
    public void init() {
        this.hashtagField.setVisible(true);
        this.btn1.setText("Delete someone");
        this.btn2.setText("Log Out");
        this.btn3.setVisible(false);
        this.loggedAsLabel.setText(TwitterClientWrapper.getSession().uid);
        this.title.setText("Twitter 2 Admin Feed");
    }
}
