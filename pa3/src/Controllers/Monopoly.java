package Controllers;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.net.URL;

public class Monopoly extends Application {

    /**
     * @param primaryStage The stage that is passed from the JavaFX runtime
     * @throws Exception Exceptions that can occur in this method are mostly dealing with the URL of the view being loaded
     */
    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader();
        Scene scene = new Scene(new StackPane());

        URL pathToOpeningView = new URL("file:src/Views/TabbedView.fxml");
        loader.setLocation(pathToOpeningView);
        scene.setRoot(loader.load());
        MainController controller = loader.getController();
        controller.init();
        /*
        Parent root = loader.load();
        Scene scene = new Scene(root);
         */

        primaryStage.setScene(scene);

        primaryStage.setTitle("Main Menu");

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }


}
