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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
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
    @FXML TabPane tabPane;

    @FXML Tab menuTab;

    @FXML Tab gameTab;
    @FXML  GameController gameViewController;

    @FXML Tab turnOrderTab;
    @FXML  TurnOrderController turnOrderViewController;



    @FXML private ChoiceBox<String> playerChoiceBox;

    @FXML private ChoiceBox<String> timerChoiceBox;

    @FXML Button startGameButton;

// =====================================================================================================================
//  ===================================== Class variables needed for start method ======================================

    Game game;

    int numberOfPlayers;

    int timerValue;


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
        MainMenuController controller = loader.getController();
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

    @FXML
    private void initialize() {
        gameViewController.injectMainMenuController(this);
        turnOrderViewController.injectMainMenuController(this);
    }

    /**
     * Event handler for when the start button has been clicked
     */
    @FXML
    public void startButtonClicked(Event e) {
        // initialize the data for the Game object that will be used by the GameController
        initGameData();
        // initialize the TurnOrderViewController with the number of players so that it can roll the correct amount of
        //      dice
        // switch active tab to the TurnOrderView tab
        tabPane.getSelectionModel().select(turnOrderTab);
        // initialize the TurnOrderController with the number of players in the game


        // Select the game tab
        tabPane.getSelectionModel().select(gameTab);
        // Transfer controller ownership to the GameController
        // call the game tab page controller's init function
//        this.gameTabPageController = new GameController(this.game);
//        gameTabPageController.init();

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
        this.timerValue = Integer.parseInt(timerSplit[0]);

        System.out.println("Game timer length Selected: " + this.timerValue);



        // initialize the game
        this.game = new Game(this.numberOfPlayers, this.timerValue);

    }




}
