package batman.gui;

import batman.core.Batman;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {
    private Batman batman = new Batman("./data", "tasks.csv");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            fxmlLoader.<MainWindow>getController().setBatman(batman);  // inject the Duke instance
            fxmlLoader.<MainWindow>getController().setStage(stage);
            batman.initApp();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
