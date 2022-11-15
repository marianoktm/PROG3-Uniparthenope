package Client.JavaFXGUI.Classes;

import Client.JavaFXGUI.Controllers.Controller;

import java.io.IOException;

/**
 * A WRAPPER class for opening new stages. Provides wrapper functions for StageFacade and DialogStage classes.
 */
public class PopUpWrapper {
    /**
     * Shows a new stage.
     * @param fxmlName the FXML name (without .fxml) of the new stage.
     * @param windowName the window name of the new stage.
     */
    public static void showStage(String fxmlName, String windowName) {
        try { new StageFacade(fxmlName, windowName).show(); }
        catch (IOException e) { e.printStackTrace(); }
    }

    /**
     * Shows a new stage after setting a controller for its behavior and handling.
     * @param fxmlName the FXML name (without .fxml) of the new stage.
     * @param windowName the window name of the new stage.
     * @param controller the controller that will be used for the stage.
     */
    public static void setControllerAndShowStage(String fxmlName, String windowName, Controller controller) {
        try { new StageFacade(fxmlName, windowName, controller).show(); }
        catch (IOException e) { e.printStackTrace(); }
    }

    /**
     * Shows a new dialog with a message.
     * @param fxmlName the FXML name (without .fxml) of the new stage.
     * @param windowName the window name of the new stage.
     * @param message the message that will be showed.
     */
    public static void showDialog(String fxmlName, String windowName, String message) {
        try { new DialogStage(fxmlName, windowName, message).show(); }
        catch (IOException ex) { ex.printStackTrace(); }
    }

    /**
     * Shows a new dialog based on Dialog.fxml.
     * @param windowName the window name of the new stage.
     * @param message the message that will be showed.
     */
    public static void showDialog2(String windowName, String message) {
        showDialog("Dialog", windowName, message);
    }
}
