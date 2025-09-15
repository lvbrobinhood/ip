package batman.gui;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * Represents a dialog box consisting of an {@code ImageView} to represent the speaker's face
 * and a {@code Label} containing text from the speaker.
 * <p>
 * This class is used to create a dialog box that displays a message with an image. The dialog box
 * can be flipped to display messages from the user and the bot in a conversational style.
 * </p>
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Private constructor that initializes a dialog box with text and an image.
     * <p>
     * This constructor loads the FXML layout for the dialog box and sets the text and image.
     * </p>
     *
     * @param text the text content of the dialog box
     * @param img the image to display in the dialog box
     */
    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        displayPicture.setImage(img);
    }

    /**
     * Flips the dialog box such that the {@code ImageView} is on the left and text is on the right.
     * <p>
     * This method is used to adjust the layout for messages from the bot by reversing the order
     * of the components (image and text).
     * </p>
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
        dialog.getStyleClass().add("reply-label");
    }

    /**
     * Creates a dialog box for user input.
     *
     * @param text the text content of the user's message
     * @param img the image to display for the user
     * @return a {@code DialogBox} representing the user's message
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Creates a dialog box for the bot's response.
     * <p>
     * This method also flips the dialog box so that the bot's message appears on the correct side.
     * </p>
     *
     * @param text the text content of the bot's message
     * @param img the image to display for the bot
     * @return a {@code DialogBox} representing the bot's message
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }
}
