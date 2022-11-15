package Client.JavaFXGUI.Controllers;

import Client.JavaFXGUI.Classes.PopUpWrapper;
import Client.JavaFXGUI.Classes.StageFacade;
import Shared.ErrorHandling.Exceptions.FollowException;
import Shared.Packet.Packet;
import Shared.Packet.RequestCode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class FollowController extends TargetUserOperationController {

    /**
     * @param event
     * @throws IOException
     * @throws FollowException
     */
    @FXML
    @Override
    protected void submitBtnClick(ActionEvent event) throws IOException, FollowException {
        System.out.println("Submit Clicked.");

        String toFollow = usernameField.getText();

        List<String> packetData = new ArrayList<>();
        packetData.add(toFollow);

        sendSessionedPacket(RequestCode.USER_FOLLOW, packetData);
        Packet followResult = getAndDisconnect();

        if (followResult.isSuccessful) {
            System.out.println("User follow success!");

            String successMessage = toFollow + " followed!";
            PopUpWrapper.showDialog2("Success", successMessage);

            StageFacade.closeStageFromBtn(submitBtn);
        }
        else {
            switch (followResult.errorCode) {
                case USER_NOT_FOUND -> throw new FollowException("User " + toFollow + " not found.");
                case ALREADY_FOLLOWING -> throw new FollowException("You're already following " + toFollow + "!");
                default -> throw new FollowException("An unknown error occurred trying to follow. Retry later...");
            }
        }
    }

    /**
     *
     */
    @Override
    public void init() {
        this.usernameField.setPromptText("Who do you want to follow?");
        this.submitBtn.setText("Follow!");
        this.title.setText("Follow Someone");
    }

}

