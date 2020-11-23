package Controllers;

import Models.*;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.util.LinkedList;

public class GameController {

    @FXML private TabPane tabPane;
    @FXML private Tab gameTab;
//  ListView object that will display the properties the player owns
    @FXML private ListView<String> gamePropertyListView;
// =====================================================================================================================
//    Buttons for trading with each player
    @FXML
    private Button tradeWithPlayer1;
    @FXML
    private Button tradeWithPlayer2;
    @FXML
    private Button tradeWithPlayer3;
    @FXML
    private Button tradeWithPlayer4;
    @FXML
    Button rollDiceButton;
// =====================================================================================================================
// ============================================ Label FXML fields ======================================================
    @FXML
    Label currentPlayerTileLabel;
    @FXML
    Label currentPlayerPropertyListViewLabel;
    @FXML
    Label currentPlayerBalanceLabel;

    @FXML
    GridPane boardGridPane;


    private Game game;
    private int numberOfPlayers;
    private int timerValue;

    private MenuController menuController;


//  Injects the menu controller into the game controller and begins the game
    public void injectMenuController(MenuController menuController) {
        this.menuController = menuController;
        this.numberOfPlayers = menuController.numberOfPlayers;
        this.timerValue = menuController.timerValue;
        startGame();

    }

    public void startGame() {
        this.game = new Game(this.numberOfPlayers, this.timerValue);
//        for (int i = 0; i < this.numberOfPlayers; i++) {
//        }
        setActivePlayer();
    }

//  Logic for things that must be changed when the next players turn arrives:
//      - Change the current player in game class
//      - Change the

    public void setActivePlayer() {
        String playerTile = this.game.getCurrentPlayer().getCurrentTile().getName();
        String tileString = "Current Tile: ";
        currentPlayerTileLabel.textProperty().setValue(tileString.concat(playerTile));
        currentPlayerBalanceLabel.textProperty().setValue("Account Balance: $" + Integer.toString(this.game.getCurrentPlayer().getAccBalance()));
        addPlayerPropertiesToListView(this.game.getCurrentPlayer());
    }

//  when the turn changes, remove the properties in the list view from the other player and add the properties for the
//  new current player
    private void addPlayerPropertiesToListView(Player tradePlayer) {
        int deedArrayLength = tradePlayer.playerDeeds.length;

        for (int i = 0; i < deedArrayLength; i++) {
            for (int j = 0; j < tradePlayer.playerDeeds[i].getCurrentNumProperties(); j++) {
                Deed playerDeed  = tradePlayer.playerDeeds[i].getPropertiesInSet()[i];
                gamePropertyListView.getItems().add(playerDeed.getName());
            }
        }
    }

   @FXML
   void rollDiceButtonClicked(Event e) {
        int playerRoll = game.gameDice.roll();
        rollDiceButton.setText("You rolled: " + playerRoll);
        setActivePlayer();


   }
    // REMEMBER TO ADD [ onMouseClicked="#addPropertyToTrade" ] back to the GameView.fxml line for the ListView

//    @FXML
//    private void addPropertyToTrade(Event e) {
//        // handle the event here and add the selected item to the set of property's involved in the trade
//
//    }
}
