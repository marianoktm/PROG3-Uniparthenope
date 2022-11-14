package Client.JavaFXGUI.Controllers;

import Client.ClientClass.TwitterClientWrapper;
import Client.JavaFXGUI.Classes.PopUpWrapper;
import Client.JavaFXGUI.Classes.StageFacade;
import Shared.ErrorHandling.Exceptions.LoginException;
import Shared.Packet.Packet;
import Shared.Packet.RequestCode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoginController extends ConnectedUIController {

    public LoginController() {
        System.out.println("UserLoginController instantiated.");
    }

    @FXML
    private CheckBox isAdminCheck;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button submitLoginBtn;

    @FXML
    private Button registerInsteadBtn;

    @FXML
    private TextField usernameField;

    @FXML
    void submitBtnClick(ActionEvent event) throws IOException, LoginException {
        System.out.println("Submit Login Button Clicked.");

        String password = passwordField.getText();
        String username = usernameField.getText();

        System.out.println("Got username: " + username);
        System.out.println("Got password: " + password);

        if (username.isEmpty() || password.isEmpty()) {
            System.out.println("Username or password are empty.");
            throw new LoginException("You must fill both username and password fields!");
        }
        else {
            System.out.println("Username and Password are both not empty.");

            List<String> packetData = new ArrayList<>();
            packetData.add(username);
            packetData.add(password);

            if (isAdminCheck.isSelected()) // ADMIN LOGIN
                sendSessionlessPacket(RequestCode.ADMIN_LOGIN, packetData);
            else // USER LOGIN
                sendSessionlessPacket(RequestCode.USER_LOGIN, packetData);

            Packet loginResult = getAndDisconnect();

            if (loginResult.isSuccessful) {
                System.out.println("Login success! New session generated.");
                TwitterClientWrapper.setSession(loginResult.session);
                System.out.println("Session data: ");
                System.out.println(TwitterClientWrapper.getSession());

                FeedController feedController;
                String feedWindowName;
                if (isAdminCheck.isSelected()) { // Open Admin Feed
                    feedController = new AdminFeedController();
                    feedWindowName = "Admin Feed";
                }
                else { // Open User Feed
                    feedController = new UserFeedController();
                    feedWindowName = "Feed";
                }

                PopUpWrapper.setControllerAndShowStage("Feed", feedWindowName, feedController);
                feedController.init();

                StageFacade.closeStageFromBtn(submitLoginBtn);
            }
            else {
                System.out.println("No login.");
                throw new LoginException("Login error... " + loginResult.errorCode);
            }
        }
    }

    @FXML
    void registerInsteadBtnClick(ActionEvent event) {
        PopUpWrapper.showStage("UserRegister", "Register");
        StageFacade.closeStageFromBtn(registerInsteadBtn);
    }

}


