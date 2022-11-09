package Client.JavaFXGUI.Controllers;

import Client.JavaFXGUI.Classes.DialogStage;
import Client.JavaFXGUI.Classes.Home;
import Client.JavaFXGUI.Classes.StageFacade;
import Shared.ErrorHandling.ErrorCode;
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

public class UserRegisterController {

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
        try { new StageFacade("UserLogin", "Login").show(); }
        catch (IOException e) { e.printStackTrace(); }

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

            // Data lenght check before sending to server
            if (username.length() > 25) throw new RegisterException("Username must be 25 chars or less!");
            if (password.length() < 8) throw new RegisterException("Password must be at least 8 chars long!");
            if (email.length() > 255) throw new RegisterException("255 char email is too long!");

            List<String> registerData = new ArrayList<>();
            registerData.add(username);
            registerData.add(email);
            registerData.add(password);

            Packet registerPacket = new Packet(RequestCode.USER_REGISTER, Home.session, registerData, null, ErrorCode.NONE);

            Home.client.connect();
            Home.client.sendPacket(registerPacket);

            Packet registerResult = Home.client.getPacket();
            Home.client.disconnect();

            if (registerResult.isSuccessful) {
                System.out.println("Register successful!");

                String successMessage = "Register success! Now login.";

                try { new StageFacade("UserLogin", "Login").show(); }
                catch (IOException e) { e.printStackTrace();}

                try { new DialogStage("Dialog", "Success", successMessage).show(); }
                catch (IOException ex) { ex.printStackTrace(); }

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
}
