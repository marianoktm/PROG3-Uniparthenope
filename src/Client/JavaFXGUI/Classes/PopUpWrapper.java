package Client.JavaFXGUI.Classes;

import Client.JavaFXGUI.Controllers.Controller;

import java.io.IOException;

public class PopUpWrapper {
    public static void showStage(String fxmlName, String windowName) {
        try { new StageFacade(fxmlName, windowName).show(); }
        catch (IOException e) { e.printStackTrace(); }
    }

    public static void setControllerAndShowStage(String fxmlName, String windowName, Controller controller) {
        try { new StageFacade(fxmlName, windowName, controller).show(); }
        catch (IOException e) { e.printStackTrace(); }
    }

    public static void showDialog(String fxmlName, String windowName, String message) {
        try { new DialogStage(fxmlName, windowName, message).show(); }
        catch (IOException ex) { ex.printStackTrace(); }
    }

    public static void showDialog2(String windowName, String message) {
        showDialog("Dialog", windowName, message);
    }
}
