package batman.gui;

import javafx.application.Application;

/**
 * A launcher class to work around classpath issues when running the application.
 * <p>
 * This class serves as the entry point for launching the JavaFX application
 * by invoking {@link Application#launch(Class, String[])} with the main application class.
 * </p>
 */
public class Launcher {
    /**
     * Main method to launch the JavaFX application.
     * <p>
     * This method bypasses classpath issues by using the {@link Application#launch(Class, String[])}
     * method, which starts the JavaFX application with the {@code Main} class.
     * </p>
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
