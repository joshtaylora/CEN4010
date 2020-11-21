package Controllers;

import Models.Game;
import Models.Player;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class GameController {

    @FXML private TabPane tabPane;
    @FXML private Tab gameTab;
//  ListView object that will display the properties the player owns
    @FXML private ListView<Models.Deed> gamePropertyListView;

//    Buttons for trading with each player
    @FXML private Button tradeWithPlayer1;
    @FXML private Button tradeWithPlayer2;
    @FXML private Button tradeWithPlayer3;
    @FXML private Button tradeWithPlayer4;

    private Game game;


    public void init(Game game) {
        this.game = game;
        int numPlayers = game.getNumPlayers();
        Player startingPlayer = game.getStartingPlayer();


    }

    private void addPlayerPropertiesToListView(Player tradePlayer) {
        int deedArrayLength = tradePlayer.playerDeeds.length;

        for (int i = 0; i < deedArrayLength; i++) {
            for (int j = 0; j < tradePlayer.playerDeeds[i].getCurrentNumProperties(); j++) {
                gamePropertyListView.getItems().add(tradePlayer.playerDeeds[i].getPropertiesInSet()[i]);
            }
        }
    }

    @FXML
    private void addPropertyToTrade(Event e) {
        // handle the event here and add the selected item to the set of property's involved in the trade

    }
}
