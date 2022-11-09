package Client.JavaFXGUI.Controllers;

import Client.JavaFXGUI.Classes.DialogStage;
import Client.JavaFXGUI.Classes.Home;
import Client.JavaFXGUI.Classes.StageFacade;
import Shared.ErrorHandling.ErrorCode;
import Shared.ErrorHandling.Exceptions.SubmitTweetException;
import Shared.Packet.Packet;
import Shared.Packet.RequestCode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SubmitTweetController {

    @FXML
    private Button cancelBtn;

    @FXML
    private TextField hashtagField;

    @FXML
    private TextArea messageField;

    @FXML
    private Button submitTweetBtn;

    @FXML
    void cancelBtnClick(ActionEvent event) {
        StageFacade.closeStageFromBtn(cancelBtn);
    }

    @FXML
    void submitTweetBtnClick(ActionEvent event) throws SubmitTweetException, IOException {
        String hashtag = hashtagField.getText();
        String message = messageField.getText();

        if (message.length() > 140) throw new SubmitTweetException("Your tweet is too long! Max 140 chars!");

        List<String> tweetData = new ArrayList<>();
        tweetData.add(hashtag);
        tweetData.add(message);

        Packet tweetPacket = new Packet(RequestCode.SUBMIT_TWEET, Home.session, tweetData, null, ErrorCode.NONE);

        Home.client.connect();
        Home.client.sendPacket(tweetPacket);

        Packet tweetResult = Home.client.getPacket();
        Home.client.disconnect();

        if (tweetResult.isSuccessful) {
            System.out.println("Tweet submit success!");

            String successMessage = "Tweet submitted!";

            try { new DialogStage("Dialog", "Success", successMessage).show(); }
            catch (IOException ex) { ex.printStackTrace(); }

            StageFacade.closeStageFromBtn(submitTweetBtn);
        }
        else {
            throw new SubmitTweetException("An error occurred while submitting your tweet. Retry later.");
        }
    }
}
