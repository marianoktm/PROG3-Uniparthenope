package Client.JavaFXGUI.Controllers;

import Client.JavaFXGUI.Classes.StageFacade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class DialogController {

    public DialogController() {
        System.out.println("DialogController instantiated.");
    }

    @FXML
    private Label messageLabel;

    @FXML
    private Button okayButton;

    @FXML
    void okayBtnClick(ActionEvent event) {
        System.out.println("Okay Button Clicked.");
        StageFacade.closeStageFromBtn(okayButton);
    }

    public void setMessageLabel(String message) {
        this.messageLabel.setText(message);
    }

}