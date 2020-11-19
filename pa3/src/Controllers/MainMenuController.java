package Controllers;

import Models.Game;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;

public class MainMenuController extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        /*
        * Step 1) Load FXML document as the parent (root) node
        * Step 2) Make a new scene using the root as the argument
        * Step 3) Set the scene using primaryStage.setScene
        *
         */
        FXMLLoader loader = new FXMLLoader();

        URL pathToOpeningView = new URL("file:src/Views/OpeningView.fxml");
        loader.setLocation(pathToOpeningView);
        Parent root = loader.load();
        Scene scene = new Scene(root);

        primaryStage.setScene(scene);

        primaryStage.setTitle("Main Menu");

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    @FXML
    ChoiceBox<String> playerChoiceBox;
    /*
    *   TODO Add FXML event event handler for the ChoiceBox for number of players
    *    - The ID for the choicebox is playerChoiceBox
     */
    @FXML
    void initialize() {
    }
}
