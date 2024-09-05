package cr.ac.una.mailapp;

import cr.ac.una.mailapp.util.FlowController;
import javafx.application.Application;
import cr.ac.una.tarea.controller.LoginController;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    LoginController loginController;

    @Override
    public void start(Stage stage) throws IOException {
        FlowController.getInstance().InitializeFlow(stage, null);
        loginController.initialize();
    }

    public static void main(String[] args) {
        launch();
    }

}