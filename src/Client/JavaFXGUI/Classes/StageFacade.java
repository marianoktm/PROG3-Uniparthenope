package Client.JavaFXGUI.Classes;

import Client.JavaFXGUI.Controllers.Controller;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class StageFacade {
    private static final String FXML_PATH = "../FXMLs/";
    private static final String TWITTER = "Twitter 2";
    private static final String FXML_EXT = ".fxml";

    protected final Stage STAGE;
    protected final FXMLLoader LOADER;

    public StageFacade(String fxmlName, String windowName) throws IOException {
        String fxmlToLoad = FXML_PATH + fxmlName + FXML_EXT;
        System.out.println("Trying to load fxml: " + fxmlToLoad);

        LOADER = new FXMLLoader(getClass().getResource(fxmlToLoad));

        STAGE = new Stage();

        STAGE.setTitle("Twitter 2 " + windowName);
        STAGE.setScene(new Scene(LOADER.load()));
    }

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

    public void show() {
        STAGE.show();
    }

    public void setController(Controller controller) {
        LOADER.setController(controller);
    }

    public static void closeStageFromBtn(Button button) {
        ((Stage) button.getScene().getWindow()).close();
    }

    public static String getFXMLCompletePath(String fxmlName) {
        return FXML_PATH + fxmlName + FXML_EXT;
    }

    public static FXMLLoader getLoader(String fxmlName) {
        return new FXMLLoader(StageFacade.class.getResource(getFXMLCompletePath(fxmlName)));
    }
}
