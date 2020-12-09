package Controllers;

import Models.*;
import Resources.ResourceManager;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;

public class GameController {

//  @FXML private TabPane tabPane;
//  @FXML private Tab gameTab;
//  ListView object that will display the properties the player owns
    @FXML private ListView<String> gamePropertyListView;
// =====================================================================================================================
//    Buttons for trading with each player
    @FXML
    private Button player1TradeButton;
    @FXML
    private Button player2TradeButton;
    @FXML
    private Button player3TradeButton;
    @FXML
    private Button player4TradeButton;
    @FXML
    private Button rollDiceButton;
    @FXML
    private TextArea diceRollResultsTextArea;
    @FXML
    private Button endTurnButton;
    @FXML
    private ImageView rollDiceImage1;
    @FXML
    private ImageView rollDiceImage2;
// =====================================================================================================================
// ============================================ Label FXML fields ======================================================
    @FXML
    Label currentPlayerTileLabel;
    @FXML
    Label currentPlayerPropertyListViewLabel;
    @FXML
    Label player1Acct;
    @FXML
    Label player2Acct;
    @FXML
    Label player3Acct;
    @FXML
    Label player4Acct;

// =====================================================================================================================
// ============================================ Board FXML fields ======================================================
    @FXML
    GridPane boardGridPane;

// =====================================================================================================================
// ============================================ Player Info FXML fields ================================================
    @FXML
    AnchorPane playerdog;
    @FXML
    AnchorPane playershoe;
    @FXML
    AnchorPane playercar;
    @FXML
    AnchorPane playerthim;


// =====================================================================================================================
// ============================================ Global Variables =======================================================
    MainController mainController;
    TilePopController tileViewController;
    private Game game;
    private int numberOfPlayers;
    private int timerValue;
    int currentPlayerIndex;
    boolean consecutiveTurn = false;
    ArrayList<Integer> playerTurnList;

//  Injects the menu controller into the game controller and begins the game
    void injectMenuController(MenuController menuController) {
        this.numberOfPlayers = menuController.numberOfPlayers;
        this.timerValue = menuController.timerValue;
        this.mainController = menuController.mainController;
        this.tileViewController = menuController.mainController.tileViewController;
        startGame();

    }

    void initPlayerTurns(ArrayList<Integer> playerTurnList) {
        this.playerTurnList = playerTurnList;
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
        addPlayerPropertiesToListView(this.game.getCurrentPlayer());
    }

    private void updateBalance(){
        switch(currentPlayerIndex){
            case 0:
                player1Acct.setText("$" + Integer.toString(game.getCurrentPlayer().getAccBalance()));
                break;
            case 1:
                player2Acct.setText("$" + Integer.toString(game.getCurrentPlayer().getAccBalance()));
                break;
            case 2:
                player3Acct.setText("$" + Integer.toString(game.getCurrentPlayer().getAccBalance()));
                break;
            case 3:
                player4Acct.setText("$" + Integer.toString(game.getCurrentPlayer().getAccBalance()));
                break;
            default:
                break;
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
/* ================================================================================================================== */
   @FXML
   void rollDiceButtonClicked(Event e) {
       if(consecutiveTurn){
           rollDiceButton.setText("Roll Dice");
       }
        Player rollingPlayer = game.getCurrentPlayer();
        int initDoubles = rollingPlayer.getDoubles();
        int postDoubles;
        int ownership = 0;
        int cost = 0;
        // check to make sure that the player hasn't already rolled
        if (!rollingPlayer.getRollStatus()) {
            int result = game.playerRoll();

            rollDiceImage1.setImage(game.dieImage1);
            rollDiceImage2.setImage(game.dieImage2);

            setActivePlayer();

            //check ownership if the tile can be owned, and money involved
            ownership = checkOwnership(rollingPlayer.getCurrentTile(), rollingPlayer, result)[0];
            cost = checkOwnership(rollingPlayer.getCurrentTile(), rollingPlayer, result)[1];

            //open tilepop with appropriate elements
            this.tileViewController.tileSetup(rollingPlayer.getCurrentTile().getName(), rollingPlayer.getCurrentTile().getType(), ownership, cost);
            mainController.addTileTab();

            //TODO: add money to reciveing player is case of 2
            //get the array detailing how much the player gained or lost, if any
            int[] paymentInfo = this.tileViewController.getMoneyInfo();
            //if: player is to lose money or stay the same
            if(paymentInfo[0] == 0){
                rollingPlayer.setAccBalance(rollingPlayer.getAccBalance() - paymentInfo[1]);
            }
            //else: player is to gain money
            else{
                rollingPlayer.setAccBalance(rollingPlayer.getAccBalance() + paymentInfo[1]);
            }
            updateBalance();

            //see if player rolled doubles during this turn
            postDoubles = rollingPlayer.getDoubles();
            if(initDoubles<postDoubles){
                rollDiceButton.setText("Doubles!(" + postDoubles + "/3): Roll Again");
                consecutiveTurn = true;
            }
            else{
                //style roll dice button to indicate not rolling again
                rollDiceButton.getStyleClass().remove("rollButtonActive");
                rollDiceButton.getStyleClass().add("rollButtonInactive");
                //set message if player keeps trying to press roll
                rollDiceButton.setText("PRESS END TURN BUTTON");
                //reset variable
                consecutiveTurn = false;
            }
        }
   }

   //Player has opted to end their turn
   @FXML
    void endTurnButtonClicked(Event e) {
        //check if game time limit is up
       //if(System.currentTimeMillis() < game.timeLimit) {
           //player cannot end their turn until they have rolled
           if (game.getCurrentPlayer().getRollStatus()) {
               //reset player roll status, increment player, and display their information
               this.game.getCurrentPlayer().setRollStatus(false);
               this.game.setNextPlayer();
               setActivePlayer();

               //change active player css
               switch (currentPlayerIndex) {
                   case 0:
                       playerdog.getStyleClass().remove("activePlayerSection");
                       playerdog.getStyleClass().add("inactivePlayerSection");
                       playershoe.getStyleClass().remove("inactivePlayerSection");
                       playershoe.getStyleClass().add("activePlayerSection");
                       break;
                   case 1:
                       playershoe.getStyleClass().remove("activePlayerSection");
                       playershoe.getStyleClass().add("inactivePlayerSection");
                       if (game.getNumPlayers() > 2) {
                           playercar.getStyleClass().remove("inactivePlayerSection");
                           playercar.getStyleClass().add("activePlayerSection");
                       } else {
                           playerdog.getStyleClass().remove("inactivePlayerSection");
                           playerdog.getStyleClass().add("activePlayerSection");
                       }
                       break;
                   case 2:
                       playercar.getStyleClass().remove("activePlayerSection");
                       playercar.getStyleClass().add("inactivePlayerSection");
                       if (game.getNumPlayers() == 4) {
                           playerthim.getStyleClass().remove("inactivePlayerSection");
                           playerthim.getStyleClass().add("activePlayerSection");
                       } else {
                           playerdog.getStyleClass().remove("inactivePlayerSection");
                           playerdog.getStyleClass().add("activePlayerSection");
                       }
                       break;
                   case 3:
                       playerthim.getStyleClass().remove("activePlayerSection");
                       playerthim.getStyleClass().add("inactivePlayerSection");
                       playerdog.getStyleClass().remove("inactivePlayerSection");
                       playerdog.getStyleClass().add("activePlayerSection");
                       break;
                   default:
                       break;
               }

               //set currentPlayerIndex
               if (currentPlayerIndex < game.getNumPlayers() - 1) {
                   currentPlayerIndex++;
               } else {
                   currentPlayerIndex = 0;
               }

               //reset buttons
               rollDiceButton.setText("Roll Dice");
               rollDiceButton.getStyleClass().remove("rollButtonInactive");
               rollDiceButton.getStyleClass().add("rollButtonActive");
           }
//       }
//       else{
//           //TODO:  check  winner and display
//       }

   }

    /**
     * Event handler method for when Player 1 presses their trade button
     * @param e the event for when Player 1 clicks the trade button
     */
    @FXML
    void player1TradeButtonClicked(Event e) {
        String currentPlayerName = game.getCurrentPlayer().getName();
        if (currentPlayerName.equals("player1")) {
            return;
        }
        // use the main controller to switch the selected tab to the trade view
        this.mainController.tabPane.getSelectionModel().select(this.mainController.tradeTab);
        // call method in TradeController to populate the fields for the correct players
        this.mainController.tradeViewController.populateTradeView(game.getCurrentPlayer(), game.getPlayer(1));


    }
    /**
     * Event handler method for when Player 2 presses their trade button
     * @param e the event for when Player 2 clicks the trade button
     */
    @FXML
    void player2TradeButtonClicked(Event e) {
        String currentPlayerName = game.getCurrentPlayer().getName();

        if (currentPlayerName.equals("player2")) {
            return;
        }
        // use the main controller to switch the selected tab to the trade view
        this.mainController.tabPane.getSelectionModel().select(this.mainController.tradeTab);
        // call method in TradeController to populate the fields for the correct players
        this.mainController.tradeViewController.populateTradeView(game.getCurrentPlayer(), game.getPlayer(2));
    }

    @FXML
    void player3TradeButtonClicked(Event e) {
        String currentPlayerName = game.getCurrentPlayer().getName();

        if (currentPlayerName.equals("player3")) {
            return;
        }

        // use the main controller to switch the selected tab to the trade view
        this.mainController.tabPane.getSelectionModel().select(this.mainController.tradeTab);
        // call method in TradeController to populate the fields for the correct players
        this.mainController.tradeViewController.populateTradeView(game.getCurrentPlayer(), game.getPlayer(3));

    }

    @FXML
    void player4TradeButtonClicked(Event e) {
        String currentPlayerName = game.getCurrentPlayer().getName();

        if (currentPlayerName.equals("player4")) {
            return;
        }

        // use the main controller to switch the selected tab to the trade view
        this.mainController.tabPane.getSelectionModel().select(this.mainController.tradeTab);
        // call method in TradeController to populate the fields for the correct players
        this.mainController.tradeViewController.populateTradeView(game.getCurrentPlayer(), game.getPlayer(4));

    }

   private int[] checkOwnership(Tile current, Player curplay, int rollResult){
       //[0] ownership, [1] cost
       //0= no one owns this, 1=currentPlayer owns this, 2=another player owns this
       int[] ret = {0, 0};
       if(curplay.getCurrentTile().getType().equals("Deed")){
           Deed obj = (Deed) curplay.getCurrentTile();
           Player check = obj.getOwner();
           if(check == null){
               ret[0] = 0;
           }
           else if(check.getName().equals(curplay.getName())){
               ret[0] = 1;
           }
           else{
               ret[0] = 2;
               ret[1] = obj.calcRent();
           }
       }
       else if(curplay.getCurrentTile().getType().equals("RailRoad")){
           RailRoad obj = (RailRoad) curplay.getCurrentTile();
           Player check = obj.getOwner();
           if(check == null){
               ret[0] = 0;
           }
           else if(check.getName().equals(curplay.getName())){
               ret[0] = 1;
           }
           else{
               ret[0] = 2;
               ret[1] = obj.calcRent();
           }
       }
       else if(curplay.getCurrentTile().getType().equals("Utility")){
           Utility obj = (Utility) curplay.getCurrentTile();
           Player check = obj.getOwner();
           if(check == null){
               ret[0] = 0;
           }
           else if(check.getName().equals(curplay.getName())){
               ret[0] = 1;
           }
           else{
               ret[0] = 2;
               ret[1] = obj.calcRent(rollResult);
           }
       }
       return ret;
   }
    // REMEMBER TO ADD [ onMouseClicked="#addPropertyToTrade" ] back to the GameView.fxml line for the ListView

//    @FXML
//    private void addPropertyToTrade(Event e) {
//        // handle the event here and add the selected item to the set of property's involved in the trade
//
//    }
}
