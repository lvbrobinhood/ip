package batman.gui;

import batman.core.Batman;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * A GUI for Batman task manager application using FXML.
 * <p>
 * This class launches the graphical user interface (GUI) for the Batman task manager application,
 * initializing the main window and injecting the necessary components such as the {@code Batman} instance
 * and setting up the application window properties.
 * </p>
 */
public class Main extends Application {
    private Batman batman = new Batman("./data", "tasks.csv");

    /**
     * Initializes the GUI for the Batman task manager application.
     * <p>
     * This method loads the FXML layout, sets up the scene and stage, and injects the necessary controller
     * and Batman instance into the main window.
     * </p>
     *
     * @param stage the primary stage for the JavaFX application
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            fxmlLoader.<MainWindow>getController().setBatman(batman);  // inject the Batman instance
            fxmlLoader.<MainWindow>getController().setStage(stage);
            batman.initApp();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
