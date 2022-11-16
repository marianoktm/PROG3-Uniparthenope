package Client.JavaFXGUI.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * Controller class for Tweet.fxml
 */
public class TweetController implements Controller {

    @FXML
    private Label dateField;

    @FXML
    private Label hashtagField;

    @FXML
    private Label messageField;

    @FXML
    private Label usernameField;

    public TweetController() {
        System.out.println("TweetController instantiated.");
    }

    /**
     * Sets the tweet fields.
     * @param username the tweet sender's username
     * @param hashtag the tweet's hashtag
     * @param message the actual tweet
     * @param date tweet post date
     */
    public void setFields(String username, String hashtag, String message, String date) {
        usernameField.setText(username);
        hashtagField.setText(hashtag);
        messageField.setText(message);
        dateField.setText(date);
    }
}
