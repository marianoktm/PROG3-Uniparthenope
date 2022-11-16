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
 * A controller class that provides behavior to TargetUserOperation.fxml. This controller can be used to provide ban tools to admins.
 */
public class DeleteUserController extends TargetUserOperationController {

    /**
     * {@inheritDoc} Submits the username that will be banned from the application.
     * @param event the event arisen by submitBtn.
     * @throws FollowException if the user does not exist.
     * @throws IOException if a packet can't be sent or read.
     */
    @FXML
    @Override
    protected void submitBtnClick(ActionEvent event) throws FollowException, IOException {
        System.out.println("Submit Clicked.");

        String toDelete = usernameField.getText();

        List<String> packetData = new ArrayList<>();
        packetData.add(toDelete);

        sendSessionedPacket(RequestCode.DELETE_USER, packetData);
        Packet deleteResult = getAndDisconnect();

        if (deleteResult.isSuccessful) {
            System.out.println("User delete success!");

            String successMessage = toDelete + " deleted!";
            PopUpWrapper.showDialog2("Success", successMessage);

            StageFacade.closeStageFromBtn(submitBtn);
        }
        else {
            switch (deleteResult.errorCode) {
                case USER_NOT_FOUND -> throw new FollowException("User " + toDelete + " not found.");
                default -> throw new FollowException("An unknown error occurred trying to delete. Retry later...");
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void init() {
        this.usernameField.setPromptText("Who do you want to delete?");
        this.submitBtn.setText("Delete!");
        this.title.setText("Delete someone");
    }
}
