package batman.gui;

import batman.core.Batman;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Controller for the main GUI window of the Batman task manager application.
 * <p>
 * This class manages user interaction with the graphical interface, handling
 * user input, displaying bot responses, and managing the application window.
 * </p>
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Batman batman;
    private Stage stage;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/robin.jpg"));
    private final Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/batman.jpg"));

    /**
     * Initializes the main window, binding the scroll pane to the dialog container's height.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Injects the {@code Batman} instance and displays an initial welcome message from Batman.
     *
     * @param batman the Batman instance to be used for handling input
     */
    public void setBatman(Batman batman) {
        this.batman = batman;
        dialogContainer.getChildren()
                .add(DialogBox.getDukeDialog("Hello! I'm Batman.\n" + "What can I do for you?", dukeImage));
    }

    /**
     * Sets the stage for the application window and handles the window close button (X).
     * <p>
     * This method binds the close event to trigger the exit sequence rather than the default close behavior.
     * </p>
     *
     * @param stage the stage representing the main window
     */
    public void setStage(Stage stage) {
        this.stage = stage;

        // Handle window close request
        this.stage.setOnCloseRequest(event -> {
            // Prevent default close
            event.consume();
            // Trigger exit sequence as if user typed "bye"
            handleExitCommand();
        });
    }

    /**
     * Handles user input from the text field.
     * Adds both user and bot dialogs to the conversation, clears the input field,
     * and checks if the exit command is typed to trigger the exit sequence.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = batman.processInput(input);

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();

        if (batman.isExitCommand(input)) {
            handleExitCommand();
        }
    }

    /**
     * Handles the exit sequence of the application.
     * Adds a goodbye message and closes the stage after a short delay.
     */
    private void handleExitCommand() {
        String exitMessage = "bye"; // simulate user typing "bye"
        String response = batman.processInput(exitMessage);

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(exitMessage, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );

        PauseTransition delay = new PauseTransition(Duration.seconds(1.5));
        delay.setOnFinished(e -> stage.close());
        delay.play();
    }
}
