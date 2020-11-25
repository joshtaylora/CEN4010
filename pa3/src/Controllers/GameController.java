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
import javafx.stage.Modality;
import javafx.stage.Stage;

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
    @FXML
    TextArea diceRollResultsTextArea;
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
    int currentPlayerIndex;

    private MenuController menuController;

    // TODO add button under turn TextArea to end turn
//  Injects the menu controller into the game controller and begins the game
    void injectMenuController(MenuController menuController) {
        this.menuController = menuController;
        this.numberOfPlayers = menuController.numberOfPlayers;
        this.timerValue = menuController.timerValue;
        startGame();

    }

    private void startGame() {
        currentPlayerIndex = 0;
        this.game = new Game(this.numberOfPlayers, this.timerValue);
        setActivePlayer();
        //gameLoop();
    }
    /*
    // loop in which the game is run
    private void gameLoop() {
        while(System.currentTimeMillis() < game.timeLimit) {
            // roll for the player

            // update the board to show the player's new position
            updateTokenPositionOnBoard(playerRoll);
        }
    }
    */
    private void updateTokenPositionOnBoard(int spacesToAdvanceToken) {
        String playerTile = this.game.getCurrentPlayer().getCurrentTile().getName();
        String tilePrompt = "Current Tile: ";
        // set the label for the tile the current player is on
        currentPlayerTileLabel.textProperty().setValue(tilePrompt.concat(playerTile));
    }

//  Logic for things that must be changed when the next players turn arrives:
//      - Change the current player in game class
//      - Change the

    private void setActivePlayer() {
        String playerTile = this.game.getCurrentPlayer().getCurrentTile().getName();
        String tilePrompt = "Current Tile: ";
        // set the label for the tile the current player is on
        currentPlayerTileLabel.textProperty().setValue(tilePrompt.concat(playerTile));
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
        // check to make sure that the player hasn't already rolled
        if (!game.getCurrentPlayer().getRollStatus()) {
            int spacesAdvanced = game.playerRoll();
            // advance the player on the board
            Tile tileToAdvanceTo = game.advancePlayerTile(spacesAdvanced);
            diceRollResultsTextArea.setText("Player advanced " + spacesAdvanced + " tiles.\nPlayer's current tile is now "
                    + tileToAdvanceTo.getName());
            // Now set the player's roll status to true so we don't roll for them again
            game.getCurrentPlayer().setRollStatus(true);
            setActivePlayer();
        }
        else {
            diceRollResultsTextArea.setText("Player already rolled this turn");
        }
   }

    // REMEMBER TO ADD [ onMouseClicked="#addPropertyToTrade" ] back to the GameView.fxml line for the ListView

//    @FXML
//    private void addPropertyToTrade(Event e) {
//        // handle the event here and add the selected item to the set of property's involved in the trade
//
//    }
}
