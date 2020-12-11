package Controllers;

import Models.Dice;
import Resources.ImageContainer;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.MalformedURLException;

public class TurnOrderController {

// =====================================================================================================================
// ====================================== FXML elements for the Turn Order tab =========================================

    @FXML Tab turnOrderTab; // the tab for the turn orderDice view

    @FXML TabPane tabPane;

    @FXML Tab gameTab;

    @FXML Button rollButton;

    @FXML Button continueToGameButton;

    @FXML private ImageView dieOne;
    @FXML private ImageView dieTwo;
    @FXML private ImageView dieThree;
    @FXML private ImageView dieFour;

    @FXML private Label labelOne;
    @FXML private Label labelTwo;
    @FXML private Label labelThree;
    @FXML private Label labelFour;

    @FXML private ImageView tokOne;
    @FXML private ImageView tokTwo;
    @FXML private ImageView tokThree;
    @FXML private ImageView tokFour;

    private int numberOfPlayers;
    private MainController mainController;
    private MenuController menuController;

    ImageContainer imgContainer = new ImageContainer();

    static Image[] diceImageArray;

    public void injectMainController(MainController mainController) {
        this.mainController = mainController;
        this.menuController = this.mainController.menuViewController;
    }


    @FXML
    public void rollDiceButtonClicked(Event e) throws MalformedURLException {
        this.numberOfPlayers = this.menuController.numberOfPlayers;
        displayDice();
    }


    private void displayDice() throws MalformedURLException {
        //initialize Dice
        Dice orderDice = new Dice ();

        //roll dice relevant to number of players
        int one;
        int two;
        int three;
        int four;

        Image playerADieImage = null;
        Image playerBDieImage = null;
        Image playerCDieImage = null;
        Image playerDDieImage = null;
        // switch on the numberOfPlayers global var that we got from parsing the choice box's input
        switch(this.numberOfPlayers) {
            case 2:
                orderDice.roll();
                one = orderDice.getDiceOneResult();
                playerADieImage = imgContainer.getDieImage(one);

                orderDice.roll();
                two = orderDice.getDiceOneResult();
                playerBDieImage = imgContainer.getDieImage(two);

                //display correct die image
                dieOne.setImage(playerADieImage);
                dieTwo.setImage(playerBDieImage);

                //display player orderDice on labels
                if(one>=two){
                    labeling(labelOne, tokOne, 1);
                    labeling(labelTwo, tokTwo, 2);
                }
                else{
                    labeling(labelOne, tokOne, 2);
                    labeling(labelTwo, tokTwo, 1);
                }
                break;
            case 3:
                orderDice.roll();
                one = orderDice.getDiceOneResult();
                playerADieImage = imgContainer.getDieImage(one);

                orderDice.roll();
                two = orderDice.getDiceOneResult();
                playerBDieImage = imgContainer.getDieImage(two);

                orderDice.roll();
                three = orderDice.getDiceOneResult();
                playerCDieImage = imgContainer.getDieImage(three);

                //display correct die image
                dieOne.setImage(playerADieImage);
                dieTwo.setImage(playerBDieImage);
                dieThree.setImage(playerCDieImage);

                //determine player orderDice, then update labels
                int[] sort3 = {3, 3, 3};
                int[] comp3 = {one, two, three};
                int[] sorted3 = sortOrder(sort3, comp3);
                labeling(labelOne, tokOne, sorted3[0]);
                labeling(labelTwo, tokTwo, sorted3[1]);
                labeling(labelThree, tokThree, sorted3[2]);

                break;
            case 4:
                orderDice.roll();
                one = orderDice.getDiceOneResult();
                playerADieImage = imgContainer.getDieImage(one);

                orderDice.roll();
                two = orderDice.getDiceOneResult();
                playerBDieImage = imgContainer.getDieImage(two);

                orderDice.roll();
                three = orderDice.getDiceOneResult();
                playerCDieImage = imgContainer.getDieImage(three);

                orderDice.roll();
                four = orderDice.getDiceOneResult();
                playerDDieImage = imgContainer.getDieImage(four);
                //display correct die image
                dieOne.setImage(playerADieImage);
                dieTwo.setImage(playerBDieImage);
                dieThree.setImage(playerCDieImage);
                dieFour.setImage(playerDDieImage);

                //determine player orderDice, then update labels
                int[] sort4 = {4, 4, 4, 4};
                int[] comp4 = {one, two, three, four};
                int[] sorted4 = sortOrder(sort4, comp4);
                labeling(labelOne, tokOne, sorted4[0]);
                labeling(labelTwo, tokTwo, sorted4[1]);
                labeling(labelThree, tokThree, sorted4[2]);
                labeling(labelFour, tokFour, sorted4[3]);

                break;
            default:
                break;
        }

    }

    /**
     * Helper function that is used to sort the orderDice of players based on their dice roll values
     * @param sort an area containing the max value for the orderDice that a player can be in based on their roll.
     *             Will be decremented as the roll is compared to the other player's values
     * @param comp The roll values for each player. This array is used to determine the orderDice of the players.
     * @return we return the sorted array containing the proper orderDice for the players
     */
    private static int[] sortOrder(int[] sort, int[] comp) {
        int sortLen = sort.length;
        for(int i = 0; i < sortLen; i++){
            for(int j = 0; j < sortLen; j++){
                if(comp[i] > comp[j]){
                    sort[i]--;
                }
                else if(i<j && comp[i] == comp[j]){
                    sort[i]--;
                }
            }
        }
        return sort;
    }

    //updates labels, token images, and adds player to player list
    private void labeling(Label label, ImageView imageViewContent, int position) throws MalformedURLException {
        Image one = imgContainer.getTokenImage("dog");
        Image two = imgContainer.getTokenImage("shoe");
        Image thr = imgContainer.getTokenImage("racecar");
        Image fou = imgContainer.getTokenImage("thimble");
        switch (position) {
            case 1:
                label.setText("Player 1");
                imageViewContent.setImage(one);
                break;
            case 2:
                label.setText("Player 2");
                imageViewContent.setImage(two);
                break;
            case 3:
                label.setText("Player 3");
                imageViewContent.setImage(thr);
                break;
            case 4:
                label.setText("Player 4");
                imageViewContent.setImage(fou);
                break;
            default:
                break;
        }
    }

    @FXML
    public void continueToGameButtonClicked(Event e) {
        this.menuController.initGameViewController();
        this.mainController.selectGameTab();
        this.mainController.closeTurnOrderTab();
    }

}
