package Client.JavaFXGUI.Controllers;

import Client.JavaFXGUI.Classes.Home;
import Client.JavaFXGUI.Classes.StageFacade;
import Shared.ErrorHandling.ErrorCode;
import Shared.ErrorHandling.Exceptions.LoginException;
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

public class UserLoginController {

    public UserLoginController() {
        System.out.println("UserLoginController instantiated.");
    }

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

            List<String> loginData = new ArrayList<>();
            loginData.add(username);
            loginData.add(password);

            Packet loginPacket = new Packet(RequestCode.USER_LOGIN, Home.session, loginData, null, ErrorCode.NONE);

            Home.client.connect();
            Home.client.sendPacket(loginPacket);

            Packet loginResult = Home.client.getPacket();
            Home.client.disconnect();

            if (loginResult.isSuccessful) {
                System.out.println("Login success! New session generated.");
                Home.session = loginResult.session;
                System.out.println("Session data: ");
                System.out.println(Home.session);

                try { new StageFacade("Feed", "Feed").show(); }
                catch (IOException e) { e.printStackTrace(); }

                StageFacade.closeStageFromBtn(submitLoginBtn);
            }
            else {
                System.out.println("Invalid credentials.");
                throw new LoginException("Invalid credentials! Try again.");
            }
        }
    }

    @FXML
    void registerInsteadBtnClick(ActionEvent event) {
        try { new StageFacade("UserRegister", "Register").show(); }
        catch (IOException e) { e.printStackTrace(); }

        StageFacade.closeStageFromBtn(registerInsteadBtn);
    }

}


