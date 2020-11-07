package Controllers;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MainMenuController extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Main Menu");
        GridPane rootNode = new GridPane();
        Scene baseScene = new Scene(rootNode, 500, 500);
        primaryStage.setScene(baseScene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
