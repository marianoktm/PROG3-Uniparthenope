package Client.JavaFXGUI.Controllers;

import Client.JavaFXGUI.Classes.DialogStage;
import Client.JavaFXGUI.Classes.StageFacade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class UserLoginController {

    public UserLoginController() {
        System.out.println("UserLoginController instantiated.");
    }

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button submitLoginBtn;

    @FXML
    private TextField usernameField;

    @FXML
    void submitBtnClick(ActionEvent event) {
        System.out.println("Submit Login Button Clicked.");

        String password = passwordField.getText().toString();
        String username = usernameField.getText().toString();

        System.out.println("Got username: " + username);
        System.out.println("Got password: " + password);

        if (username.isEmpty() || password.isEmpty()) {
            String errorMessage = "You must fill both username and password fields!";
            try { new DialogStage("Dialog", "Error", errorMessage).show(); }
            catch (IOException e) { e.printStackTrace(); }
        }
        else {
            System.out.println("Username and Password are both not empty.");

            //TEST TO REMOVE!!!!!!
            try { new StageFacade("Feed", "Feed").show(); }
            catch (IOException e) { e.printStackTrace(); }
        }
    }

}


