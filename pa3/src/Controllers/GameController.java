package Controllers;

import Models.Deed;
import Models.Game;
import Models.Player;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class GameController {

    @FXML private TabPane tabPane;
    @FXML private Tab gameTab;
//  ListView object that will display the properties the player owns
    @FXML private ListView<String> gamePropertyListView;

//    Buttons for trading with each player
    @FXML private Button tradeWithPlayer1;
    @FXML private Button tradeWithPlayer2;
    @FXML private Button tradeWithPlayer3;
    @FXML private Button tradeWithPlayer4;


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

    }

//  Logic for things that must be changed when the next players turn arrives:
//      - Change the current player in game class
//      - Change the

    public void setActivePlayer(Game game) {
        for (int i = 0; i < game.getNumPlayers(); i++) {

        }
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

    // REMEMBER TO ADD [ onMouseClicked="#addPropertyToTrade" ] back to the GameView.fxml line for the ListView

//    @FXML
//    private void addPropertyToTrade(Event e) {
//        // handle the event here and add the selected item to the set of property's involved in the trade
//
//    }
}
