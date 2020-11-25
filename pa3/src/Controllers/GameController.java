package Controllers;

import Models.*;
import Resources.OSValidator;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.io.File;

public class GameController {

//  @FXML private TabPane tabPane;
//  @FXML private Tab gameTab;
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
    @FXML
    Button endTurnButton;
    @FXML
    ImageView rollDiceImage1;
    @FXML
    ImageView rollDiceImage2;
// =====================================================================================================================
// ============================================ Label FXML fields ======================================================
    @FXML
    Label currentPlayerTileLabel;
    @FXML
    Label currentPlayerPropertyListViewLabel;

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
// =====================================================================================================================
   @FXML
   void rollDiceButtonClicked(Event e) {
        // check to make sure that the player hasn't already rolled
        if (!game.getCurrentPlayer().getRollStatus()) {
            int spacesAdvanced = game.playerRoll();
            // advance the player on the board
            Tile tileToAdvanceTo = game.advancePlayerTile(spacesAdvanced);
            //Update the urls for the image vies to the correct dice
            Image diceImage1 = getImage(getPathToDiceRollImage(game.gameDice.getDiceOneResult()));
            Image diceImage2 = getImage(getPathToDiceRollImage(game.gameDice.getDiceTwoResult()));
            if (diceImage1 == null && diceImage2 == null) {
                System.out.println("ERROR finding image file for dice rolls");
                System.exit(1);
            }
            rollDiceImage1.setImage(diceImage1);
            rollDiceImage2.setImage(diceImage2);
            // Now set the player's roll status to true so we don't roll for them again
            game.getCurrentPlayer().setRollStatus(true);
            rollDiceButton.getStyleClass().remove("");
            setActivePlayer();
        }
        else {
            diceRollResultsTextArea.setText("Player already rolled this turn");
        }
   }

   @FXML
    void endTurnButtonClicked(Event e) {
        // Player has opted to end their turn

   }

   private String getPathToDiceRollImage(int roll) {
        String rollImageFileName = null;
        switch(roll) {
            case(1):
                rollImageFileName = "die1.png";
                break;
            case(2):
                rollImageFileName = "die2.png";
                break;
            case(3):
                rollImageFileName = "die3.png";
                break;
            case(4):
                rollImageFileName = "die4.png";
                break;
            case(5):
                rollImageFileName = "die5.png";
                break;
            case(6):
                rollImageFileName = "die6.png";
                break;
            default:
                System.out.println("ERROR: image url for die roll not found");
                System.exit(1);
        }
        return rollImageFileName;
   }

   private Image getImage(String fileName) {
        Image returnImage = null;
        String imagePath = null;
        OSValidator osValidator = new OSValidator();
        String sysPath = System.getProperty("user.dir");
        if (osValidator.os.equals("windows")) {
            if (sysPath.contains("pa3")) {
                imagePath = sysPath.concat("\\src\\Resources\\" + fileName);
                System.out.println(imagePath);
            }
            else {
                imagePath = sysPath.concat("\\pa3\\src\\Resources\\" + fileName);
                System.out.println(imagePath);
           }
        }
        else if (osValidator.os.equals("mac")) {
            if (sysPath.contains("pa3")) {
                imagePath = sysPath.concat("/src/Resources/" + fileName);
            }
            else {
                imagePath = sysPath.concat("/pa3/src/Resources/" + fileName);
            }
        }
        else {
           System.out.println("ERROR: operating system not supported");
           System.exit(1);
        }
        try {
            File imageFile = new File(imagePath);
            String imageURLString = imageFile.toURI().toURL().toString();
            returnImage = new Image(imageURLString);
        } catch (Exception e) {
            System.out.println("ERROR: dice image file could not be found");
            System.out.println("path to image file used: " + imagePath);
            e.printStackTrace();
        }
        return returnImage;
   }

    // REMEMBER TO ADD [ onMouseClicked="#addPropertyToTrade" ] back to the GameView.fxml line for the ListView

//    @FXML
//    private void addPropertyToTrade(Event e) {
//        // handle the event here and add the selected item to the set of property's involved in the trade
//
//    }
}
