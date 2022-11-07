package Client.JavaFXGUI.Classes;

import javafx.fxml.FXMLLoader;
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

    public void show() {
        STAGE.show();
    }

    public static void closeStageFromBtn(Button button) {
        ((Stage) button.getScene().getWindow()).close();
    }
}
