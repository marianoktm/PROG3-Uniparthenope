package Client.JavaFXGUI.Controllers;

import Client.JavaFXGUI.Classes.StageFacade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class HomeController {

    public HomeController() {
        System.out.println("HomeController instantiated.");
    }

    @FXML
    private Button loginBtn;

    @FXML
    private Button registerBtn;

    @FXML
    void loginBtnClick(ActionEvent event) {
        System.out.println("Login Button Clicked.");

        try { new StageFacade("UserLogin", "Login").show(); }
        catch (IOException e) { e.printStackTrace(); }

        StageFacade.closeStageFromBtn(loginBtn);
    }

    @FXML
    void registerBtnClick(ActionEvent event) {
        System.out.println("Register Button Clicked.");

        try { new StageFacade("UserRegister", "Register").show(); }
        catch (IOException e) { e.printStackTrace(); }

        StageFacade.closeStageFromBtn(registerBtn);
    }

}
