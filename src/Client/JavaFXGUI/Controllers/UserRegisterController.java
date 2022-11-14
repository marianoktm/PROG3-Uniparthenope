package Client.JavaFXGUI.Controllers;

import Client.JavaFXGUI.Classes.PopUpWrapper;
import Client.JavaFXGUI.Classes.StageFacade;
import Shared.ErrorHandling.Exceptions.RegisterException;
import Shared.Packet.Packet;
import Shared.Packet.RequestCode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserRegisterController extends ConnectedUIController {

    @FXML
    private TextField emailField;

    @FXML
    private Button loginInsteadBtn;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button submitRegisterBtn;

    @FXML
    private TextField usernameField;

    @FXML
    void loginInsteadBtnClick(ActionEvent event) {
        PopUpWrapper.showStage("Login", "Login");

        StageFacade.closeStageFromBtn(loginInsteadBtn);
    }

    @FXML
    void submitRegisterBtnClick(ActionEvent event) throws RegisterException, IOException {
        String password = passwordField.getText();
        String username = usernameField.getText();
        String email = emailField.getText();

        System.out.println("Got username: " + username);
        System.out.println("Got email: " + email);
        System.out.println("Got password: " + password);

        if (username.isEmpty() || password.isEmpty() || email.isEmpty()) {
            System.out.println("Provide username, email and password!.");
            throw new RegisterException("You must fill both username and password fields!");
        } else {
            System.out.println("Username and Password are both not empty.");

            // Data length check before sending to server
            checkData(password, username, email);

            List<String> packetData = new ArrayList<>();
            packetData.add(username);
            packetData.add(email);
            packetData.add(password);

            sendSessionlessPacket(RequestCode.USER_REGISTER, packetData);
            Packet registerResult = getAndDisconnect();

            if (registerResult.isSuccessful) {
                System.out.println("Register successful!");

                String successMessage = "Register success! Now login.";

                PopUpWrapper.showStage("Login", "Login");

                PopUpWrapper.showDialog2("Success", successMessage);

                StageFacade.closeStageFromBtn(submitRegisterBtn);
            } else {
                System.out.println("Register error!");

                switch (registerResult.errorCode) {
                    case REGISTER_USER_EXISTS -> throw new RegisterException("User already exists!");
                    default -> throw new RegisterException("Unknown error happened on registration...");
                }
            }
        }
    }

    private void checkData(String password, String username, String email) throws RegisterException {
        if (username.length() > 25) throw new RegisterException("Username must be 25 chars or less!");
        if (password.length() < 8) throw new RegisterException("Password must be at least 8 chars long!");
        if (email.length() > 255) throw new RegisterException("255 char email is too long!");
    }
}
