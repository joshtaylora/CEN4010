package Controllers;

import Models.Dice;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
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
// =====================================================================================================================
// ========================================= FXML elements for the main TabPane ========================================
    @FXML
    public static TabPane tabPane;

    @FXML
    Tab menuTab;

    @FXML
    public static Tab gameTab;

    @FXML
    Tab turnOrderTab;

    @FXML
    private GameController gameTabPageController;

    @FXML
    private
    TurnOrderController turnOrderController;

    @FXML
    private ChoiceBox<String> playerChoiceBox;

    @FXML
    private ChoiceBox<String> timerChoiceBox;

    @FXML Button startGame;


// =====================================================================================================================
//  ===================================== Class variables needed for start method ======================================
    private FXMLLoader loader;

    private Game game;

    private int numberOfPlayers;

    private int timerVal;

    /**
     * @param primaryStage The stage that is passed from the JavaFX runtime
     * @throws Exception Exceptions that can occur in this method are mostly dealing with the URL of the view being loaded
     */

    @Override
    public void start(Stage primaryStage) throws Exception {

        this.loader = new FXMLLoader();

        URL pathToOpeningView = new URL("file:src/Views/TabbedView.fxml");
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


    /**
     * Event handler for when the start button has been clicked
     * @param e event for when the start button gets clicked
     */
    @FXML
    public void startButtonClicked(Event e) {
        // initialize the data for the Game object that will be used by the GameController
        initGameData();
        // switch active tab to the TurnOrderView tab
        tabPane.getSelectionModel().select(turnOrderTab);
        // initialize the TurnOrderController with the number of players in the game
        this.turnOrderController = new TurnOrderController(this.numberOfPlayers);


        // Select the game tab
        tabPane.getSelectionModel().select(gameTab);
        // Transfer controller ownership to the GameController
        this.loader.setController(gameTabPageController);
        // call the game tab page controller's init function
        gameTabPageController.init(this.game);

    }

    /*
     * This initializes the Game object for use by the game controller
     */
    private void initGameData() {

        // Grab the value of the selected player choice box input
        String playerChoiceBoxValue = playerChoiceBox.getValue();
        // split the value into a string part and a digit part
        String[] playerSplit = playerChoiceBoxValue.split("\\s");
        this.numberOfPlayers = Integer.parseInt(playerSplit[0]);

        System.out.println("Number of Players Selected: " + this.numberOfPlayers);

        // Grab the value of the selected timer choice box input
        String timerChoiceBoxValue = timerChoiceBox.getValue();
        // split the value into a string part and a digit part
        String[] timerSplit = timerChoiceBoxValue.split("\\s");
        this.timerVal = Integer.parseInt(timerSplit[0]);

        System.out.println("Game timer length Selected: " + this.timerVal);



        // initialize the game
        this.game = new Game(this.numberOfPlayers, this.timerVal);

    }




}
