package Client.JavaFXGUI.Classes;

import Client.JavaFXGUI.Controllers.DialogController;

import java.io.IOException;

/**
 * A FACADE class that provides easier access to Dialog creation.
 */
public class DialogStage extends StageFacade {

    /**
     * Automatically creates a new Dialog ready to be shown.
     * @param fxmlName the fxml name (without .fxml) of the dialog gui file.
     * @param windowName the new dialog window name.
     * @throws IOException when an fxml with fxmlName name can't be found.
     */
    public DialogStage(String fxmlName, String windowName, String message) throws IOException {
        super(fxmlName, windowName);

        DialogController dialogController = LOADER.getController();
        dialogController.setMessageLabel(message);
    }
}
