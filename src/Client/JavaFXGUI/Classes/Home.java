package Client.JavaFXGUI.Classes;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Home extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../FXMLs/Home.fxml")));
        primaryStage.setTitle("Twitter 2 Home");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
