package Client.JavaFXGUI.Controllers;

import Client.JavaFXGUI.Classes.PopUpWrapper;
import Client.JavaFXGUI.Classes.StageFacade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * Home controller. Users can decide to log in or register on the application.
 */
public class HomeController implements Controller {

    public HomeController() {
        System.out.println("HomeController instantiated.");
    }

    @FXML
    private Button loginBtn;

    @FXML
    private Button registerBtn;

    /**
     * Opens the Login GUI.
     * @param event the event arisen by loginBtn.
     */
    @FXML
    void loginBtnClick(ActionEvent event) {
        System.out.println("Login Button Clicked.");

        PopUpWrapper.showStage("Login", "Login");

        StageFacade.closeStageFromBtn(loginBtn);
    }

    /**
     * Opens the Registration GUI.
     * @param event the event arisen by registerBtn.
     */
    @FXML
    void registerBtnClick(ActionEvent event) {
        System.out.println("Register Button Clicked.");

        PopUpWrapper.showStage("UserRegister", "Register");

        StageFacade.closeStageFromBtn(registerBtn);
    }

}
