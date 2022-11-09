package Client.JavaFXGUI.Classes;

import Client.ClientClass.TwitterClient;
import Client.Misc.ClientConfig;
import Client.Misc.TwitterClientUtils;
import Shared.Packet.Session;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Home extends Application {
    public static TwitterClient client;
    public static Session session = new Session(null, null, null);

    public static void main(String[] args) {
        ClientConfig config = new ClientConfig(args);
        client = new TwitterClient(config.getServerRecord());

        launch(args);
    }

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

    private static void showErrorDialog(Throwable e) {
        System.out.println("Called showErrorDialog");

        try { new DialogStage("Dialog", "Error", e.getMessage()).show(); }
        catch (IOException ex) { ex.printStackTrace(); }
    }
}
