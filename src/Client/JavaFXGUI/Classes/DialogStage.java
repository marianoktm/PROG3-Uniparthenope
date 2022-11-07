package Client.JavaFXGUI.Classes;

import Client.JavaFXGUI.Controllers.DialogController;

import java.io.IOException;

public class DialogStage extends StageFacade {
    public DialogStage(String fxmlName, String windowName, String message) throws IOException {
        super(fxmlName, windowName);

        DialogController dialogController = LOADER.getController();
        dialogController.setMessageLabel(message);
    }
}
