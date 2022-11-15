package Client.JavaFXGUI.Classes;

import Client.ClientClass.ClientConfig;
import Client.Misc.TwitterClientUtils;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

/**
 * The main class of the client. Set up the whole program to run and launches the Home screen.
 */
public class Home extends Application {

    /**
     * The main method of the client. Set the program configs and launches the GUI.
     * @param args custom client_config.properties path
     */
    public static void main(String[] args) {
        ClientConfig config = new ClientConfig(args);
        launch(args);
    }

    /**
     * Starts the GUI opening Home fxml and setting a custom ExceptionHandler.
     * @param primaryStage .
     * @throws IOException if Home fxml cannot be loaded.
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        Thread.setDefaultUncaughtExceptionHandler(Home::showError);

        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../FXMLs/Home.fxml")));
            primaryStage.setTitle("Twitter 2 Home");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        }
        catch (Throwable t) {
            System.out.println("ERROR!");
            showError(Thread.currentThread(), t);
        }
    }

    /**
     * Shows the error if an uncaught exception arises. If the program is a JavaFX Application, launches showErrorDialog().
     * @param t the thread where the exception arised.
     * @param e the exception that arised.
     */
    private static void showError(Thread t, Throwable e) {
        System.out.println("Called ShowError");
        e.printStackTrace();

        Throwable rootCause = TwitterClientUtils.findRootCause(e);

        if (Platform.isFxApplicationThread()) {
            showErrorDialog(rootCause);
        } else {
            System.err.println("An unexpected error occurred in "+ t + ": " + rootCause.getMessage());

        }
    }

    /**
     * Shows a dialog with an error message about an exception that arised.
     * @param e the exception that arised.
     */
    private static void showErrorDialog(Throwable e) {
        System.out.println("Called showErrorDialog");

        try { new DialogStage("Dialog", "Error", e.getMessage()).show(); }
        catch (IOException ex) { ex.printStackTrace(); }
    }
}
