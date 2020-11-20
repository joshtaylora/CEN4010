package Controllers;

import Models.Game;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.MalformedURLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;

public class MainMenuController extends Application {

    @FXML
    ChoiceBox<String> playerChoiceBox;

    @FXML
    ChoiceBox<String> timerChoiceBox;

    @FXML Button startGame;

    private Game game;

    private Scene gameScene;

    @Override
    public void start(Stage primaryStage) throws Exception {
        /*
        * Step 1) Load FXML document as the parent (root) node
        * Step 2) Make a new scene using the root as the argument
        * Step 3) Set the scene using primaryStage.setScene
        *
         */

        FXMLLoader loader = new FXMLLoader();

        URL pathToOpeningView = new URL("file:src/Views/MainMenuView.fxml");
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
    public void startButtonClicked(Event e) {
        initGameData();
    }
    /*
     * This initializes the Game
     */
     public void initGameData() {


        // Grab the value of the selected player choice box input
        String playerChoiceBoxValue = playerChoiceBox.getValue();
        // split the value into a string part and a digit part
        String[] playerSplit = playerChoiceBoxValue.split("\\s");
        int numPlayers = Integer.parseInt(playerSplit[0]);

        System.out.println("Number of Players Selected: " + numPlayers);

        // Grab the value of the selected timer choice box input
        String timerChoiceBoxValue = timerChoiceBox.getValue();
        // split the value into a string part and a digit part
        String[] timerSplit = timerChoiceBoxValue.split("\\s");
        int timerVal = Integer.parseInt(timerSplit[0]);

        System.out.println("Game timer length Selected: " + timerVal);

        /*
        // initialize the game
        this.game = new Game(numPlayers, timerVal);
        try {
            URL pathToGameView = new URL("file:src/Views/GameView.fxml");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

         */
     }

}
