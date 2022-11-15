package Client.JavaFXGUI.Classes;

import Client.JavaFXGUI.Controllers.Controller;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * A FACADE class that provides easier access to Stage creation.
 */
public class StageFacade {
    private static final String FXML_PATH = "../FXMLs/";
    private static final String TWITTER = "Twitter 2";
    private static final String FXML_EXT = ".fxml";

    protected final Stage STAGE;
    protected final FXMLLoader LOADER;

    /**
     * Automatically creates a new Stage ready to be shown.
     * @param fxmlName the fxml name (without .fxml) of the stage gui file.
     * @param windowName the new stage window name.
     * @throws IOException when a fxml with fxmlName name can't be found.
     */
    public StageFacade(String fxmlName, String windowName) throws IOException {
        String fxmlToLoad = FXML_PATH + fxmlName + FXML_EXT;
        System.out.println("Trying to load fxml: " + fxmlToLoad);

        LOADER = new FXMLLoader(getClass().getResource(fxmlToLoad));

        STAGE = new Stage();

        STAGE.setTitle("Twitter 2 " + windowName);
        STAGE.setScene(new Scene(LOADER.load()));
    }

    /**
     * Automatically creates a new Stage ready to be shown after setting a controller for it's handling.
     * @param fxmlName the fxml name (without .fxml) of the stage gui file.
     * @param windowName the new stage window name.
     * @param controller the controller that will be used for fxml handling.
     * @throws IOException when a fxml with fxmlName can't be found.
     */
    public StageFacade(String fxmlName, String windowName, Controller controller) throws IOException {
        String fxmlToLoad = FXML_PATH + fxmlName + FXML_EXT;
        System.out.println("Trying to load fxml: " + fxmlToLoad);

        LOADER = new FXMLLoader(getClass().getResource(fxmlToLoad));

        setController(controller);
        Parent root = LOADER.load();

        STAGE = new Stage();

        STAGE.setTitle("Twitter 2 " + windowName);
        STAGE.setScene(new Scene(root));
    }

    /**
     * Shows the stage.
     */
    public void show() {
        STAGE.show();
    }

    /**
     * Set at runtime a controller for the stage.
     * @param controller the controller that will be used.
     */
    public void setController(Controller controller) {
        LOADER.setController(controller);
    }

    /**
     * Closes the current stage getting its reference from a button in the GUI.
     * @param button a button in the stage that will be closed.
     */
    public static void closeStageFromBtn(Button button) {
        ((Stage) button.getScene().getWindow()).close();
    }

    /**
     * Returns the complete path of an FXML using only it's name. NOTE! It only concatenates strings, it doesn't parse files.
     * @param fxmlName the fxml name.
     * @return a path of the fxml file.
     */
    public static String getFXMLCompletePath(String fxmlName) {
        return FXML_PATH + fxmlName + FXML_EXT;
    }
}
