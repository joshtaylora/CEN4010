package Controllers;

import Models.*;
import Resources.ImageContainer;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.Objects;

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
    @FXML
    GridPane boardGridPane;

// =====================================================================================================================
// ============================================ Board FXML fields ======================================================
    @FXML ImageView boardImage;

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
    Game game;
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


    private void startGame() {
        mainController.initTilePopController();
        currentPlayerIndex = 0;
        this.game = new Game(this.numberOfPlayers, this.timerValue);
        setActivePlayer();

    }

//    private void updateTokenPositionOnBoard(int spacesToAdvanceToken) {
//        String playerTile = this.game.getCurrentPlayer().getCurrentTile().getName();
//        String tilePrompt = "Current Tile: ";
//        // set the label for the tile the current player is on
//        currentPlayerTileLabel.textProperty().setValue(tilePrompt.concat(playerTile));
//    }


    private void setActivePlayer() {
        String playerTile = this.game.getCurrentPlayer().getCurrentTile().getName();
        String tilePrompt = "Current Tile: ";
        // set the label for the tile the current player is on
        currentPlayerTileLabel.textProperty().setValue(tilePrompt.concat(playerTile));
        resetPlayerPropertiesListView();
        addPlayerPropertiesToListView(this.game.getCurrentPlayer());
    }

    private void resetPlayerPropertiesListView() {
        this.gamePropertyListView.getItems().removeIf(Objects::nonNull);
    }

    /**
     * Method to update the balance displayed for all players
     */
    private void updateBalance(){
        int iterate = currentPlayerIndex;

        //iterate through players
        for(int i = 0; i < game.getNumPlayers(); i++) {
            if (iterate < game.getNumPlayers() - 1) {
                iterate++;
            } else {
                iterate = 0;
            }

            //update player balance
            switch (iterate) {
                case 0:
                    player1Acct.setText("$" + Integer.toString(game.getPlayerObject(1).getAccBalance()));
                    break;
                case 1:
                    player2Acct.setText("$" + Integer.toString(game.getPlayerObject(2).getAccBalance()));
                    break;
                case 2:
                    player3Acct.setText("$" + Integer.toString(game.getPlayerObject(3).getAccBalance()));
                    break;
                case 3:
                    player4Acct.setText("$" + Integer.toString(game.getPlayerObject(4).getAccBalance()));
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * Method to update the ListView that displays the properties that a player owns
     * @param tradePlayer the player
     */
    private void addPlayerPropertiesToListView(Player tradePlayer) {
        int deedArrayLength = tradePlayer.playerPropertySetArray.length;

        for (int i = 0; i < deedArrayLength; i++) {
            for (int j = 0; j < tradePlayer.playerPropertySetArray[i].getCurrentNumProperties(); j++) {
                Deed playerDeed  = tradePlayer.playerPropertySetArray[i].getPropertiesInSet()[j];
                if(playerDeed != null) {
                    gamePropertyListView.getItems().add(playerDeed.getName());
                }
            }
        }
    }
/* ================================================================================================================== */
   @FXML
   void rollDiceButtonClicked(Event e) {
       if(consecutiveTurn){
           rollDiceButton.setText("Roll Dice");
           updateBalance();
       }

        Player rollingPlayer = game.getCurrentPlayer();
        int initDoubles = rollingPlayer.getDoubles();
        int postDoubles;
        int ownership = 0;
        // check to make sure that the player hasn't already rolled
        if (!rollingPlayer.getRollStatus()) {
            int result = game.playerRoll();

            rollDiceImage1.setImage(game.dieImage1);
            rollDiceImage2.setImage(game.dieImage2);

//            setActivePlayer();

            //check ownership if the tile can be owned, and money involved
            ownership = game.checkOwnership(rollingPlayer);

            //open tilepop with appropriate elements
            mainController.addTileTab();
            this.tileViewController.initTile(rollingPlayer.getCurrentTile(), ownership, rollingPlayer, result);
            setActivePlayer();
            //see if player rolled doubles during this turn
            postDoubles = rollingPlayer.getDoubles();
             if(initDoubles < postDoubles){
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
       updateBalance();
        //check if game time limit is up
       if(game.isThereTimeLeft()) {
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
       }
       else{
           //check who won and display winner
            int winner = game.checkWinner();
            updateBalance();
            ImageContainer imgContainer = new ImageContainer();
            boardImage.setImage(imgContainer.getPlayerImage(winner));

            rollDiceButton.setVisible(false);
            endTurnButton.setVisible(false);
            boardGridPane.setVisible(false);
       }

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
        this.mainController.addTradeTab();
        // use the main controller to switch the selected tab to the trade view
        this.mainController.tabPane.getSelectionModel().select(this.mainController.tradeTab);
        // call method in TradeController to populate the fields for the correct players
        this.mainController.tradeViewController.populateTradeView(game.getCurrentPlayer(), game.getPlayerObject(1));


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
        this.mainController.addTradeTab();
        // use the main controller to switch the selected tab to the trade view
        this.mainController.tabPane.getSelectionModel().select(this.mainController.tradeTab);
        // call method in TradeController to populate the fields for the correct players
        this.mainController.tradeViewController.populateTradeView(game.getCurrentPlayer(), game.getPlayerObject(2));
    }

    @FXML
    void player3TradeButtonClicked(Event e) {
        String currentPlayerName = game.getCurrentPlayer().getName();

        if (currentPlayerName.equals("player3")) {
            return;
        }
        this.mainController.addTradeTab();
        // use the main controller to switch the selected tab to the trade view
        this.mainController.tabPane.getSelectionModel().select(this.mainController.tradeTab);
        // call method in TradeController to populate the fields for the correct players
        this.mainController.tradeViewController.populateTradeView(game.getCurrentPlayer(), game.getPlayerObject(3));

    }

    @FXML
    void player4TradeButtonClicked(Event e) {
        String currentPlayerName = game.getCurrentPlayer().getName();

        if (currentPlayerName.equals("player4")) {
            return;
        }
        this.mainController.addTradeTab();
        // use the main controller to switch the selected tab to the trade view
        this.mainController.tabPane.getSelectionModel().select(this.mainController.tradeTab);
        // call method in TradeController to populate the fields for the correct players
        this.mainController.tradeViewController.populateTradeView(game.getCurrentPlayer(), game.getPlayerObject(4));

    }


    // REMEMBER TO ADD [ onMouseClicked="#addPropertyToTrade" ] back to the GameView.fxml line for the ListView

//    @FXML
//    private void addPropertyToTrade(Event e) {
//        // handle the event here and add the selected item to the set of property's involved in the trade
//
//    }
}
