package Controllers;

import Models.Game;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;

public class MainMenuController extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        /*
        * Step 1) Load FXML document as the parent (root) node
        * Step 2) Make a new scene using the root as the argument
        * Step 3) Set the scene using primaryStage.setScene
        *
         */
        Parent root = FXMLLoader.load(getClass().getResource("Views/MainMenuView.fxml"));

        Scene scene = new Scene(root);

        primaryStage.setScene(scene);

        primaryStage.setTitle("Main Menu");

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }


}
