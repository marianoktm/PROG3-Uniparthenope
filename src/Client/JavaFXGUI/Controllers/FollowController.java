package Client.JavaFXGUI.Controllers;

import Client.JavaFXGUI.Classes.PopUpWrapper;
import Client.JavaFXGUI.Classes.StageFacade;
import Shared.ErrorHandling.Exceptions.FollowException;
import Shared.Packet.Packet;
import Shared.Packet.RequestCode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FollowController extends ConnectedUIController {

    @FXML
    private Button cancelBtn;

    @FXML
    private TextField followField;

    @FXML
    private Button submitFollowBtn;

    @FXML
    void cancelBtnClick(ActionEvent event) {
        System.out.println("Cancel Button Clicked.");
        StageFacade.closeStageFromBtn(cancelBtn);
    }

    @FXML
    void submitFollowBtnClick(ActionEvent event) throws IOException, FollowException {
        System.out.println("Submit Follow Clicked.");

        String toFollow = followField.getText();

        List<String> packetData = new ArrayList<>();
        packetData.add(toFollow);

        sendSessionedPacket(RequestCode.USER_FOLLOW, packetData);
        Packet followResult = getAndDisconnect();

        if (followResult.isSuccessful) {
            System.out.println("Tweet submit success!");

            String successMessage = toFollow + " followed!";
            PopUpWrapper.showDialog2("Success", successMessage);

            StageFacade.closeStageFromBtn(submitFollowBtn);
        }
        else {
            switch (followResult.errorCode) {
                case USER_NOT_FOUND -> throw new FollowException("User " + toFollow + " not found.");
                case ALREADY_FOLLOWING -> throw new FollowException("You're already following " + toFollow + "!");
                default -> throw new FollowException("An unknown error occurred trying to follow. Retry later...");
            }
        }
    }

}

