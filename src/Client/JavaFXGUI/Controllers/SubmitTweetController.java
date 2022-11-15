package Client.JavaFXGUI.Controllers;

import Client.JavaFXGUI.Classes.PopUpWrapper;
import Client.JavaFXGUI.Classes.StageFacade;
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

/**
 *
 */
public class SubmitTweetController extends ConnectedUIController {

    @FXML
    private Button cancelBtn;

    @FXML
    private TextField hashtagField;

    @FXML
    private TextArea messageField;

    @FXML
    private Button submitTweetBtn;

    /**
     * @param event
     */
    @FXML
    void cancelBtnClick(ActionEvent event) {
        StageFacade.closeStageFromBtn(cancelBtn);
    }

    /**
     * @param event
     * @throws SubmitTweetException
     * @throws IOException
     */
    @FXML
    void submitTweetBtnClick(ActionEvent event) throws SubmitTweetException, IOException {
        String hashtag = hashtagField.getText();
        String message = messageField.getText();

        if (message.length() > 140) throw new SubmitTweetException("Your tweet is too long! Max 140 chars!");

        List<String> packetData = new ArrayList<>();
        packetData.add(hashtag);
        packetData.add(message);

        sendSessionedPacket(RequestCode.SUBMIT_TWEET, packetData);
        Packet tweetResult = getAndDisconnect();

        if (tweetResult.isSuccessful) {
            System.out.println("Tweet submit success!");

            String successMessage = "Tweet submitted!";

            PopUpWrapper.showDialog2("Success", successMessage);

            StageFacade.closeStageFromBtn(submitTweetBtn);
        }
        else {
            throw new SubmitTweetException("An error occurred while submitting your tweet. Retry later.");
        }
    }
}
