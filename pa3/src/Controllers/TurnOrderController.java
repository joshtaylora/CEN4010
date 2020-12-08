package Controllers;

import Models.Dice;
import Resources.ImageContainer;
import Resources.OSValidator;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;

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

    private MenuController menuController;

    static Image[] diceImageArray;

    public void injectMenuController(MenuController menuController) {
        this.menuController = menuController;
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

        Image playerAImage = null;
        Image playerBImage = null;
        Image playerCImage = null;
        Image playerDImage = null;
        // switch on the numberOfPlayers global var that we got from parsing the choice box's input
        switch(this.numberOfPlayers) {
            case 2:
                orderDice.roll();
                one = orderDice.getDiceOneResult();
                playerAImage = orderDice.getDiceRollImages()[0];

                orderDice.roll();
                two = orderDice.getDiceOneResult();
                playerBImage = orderDice.getDiceRollImages()[0];

                //display correct die image
                dieOne.setImage(playerAImage);
                dieTwo.setImage(playerBImage);

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
                playerAImage = orderDice.getDiceRollImages()[0];

                orderDice.roll();
                two = orderDice.getDiceOneResult();
                playerBImage = orderDice.getDiceRollImages()[0];

                orderDice.roll();
                three = orderDice.getDiceOneResult();
                playerCImage = orderDice.getDiceRollImages()[0];

                //display correct die image
                dieOne.setImage(playerAImage);
                dieTwo.setImage(playerBImage);
                dieThree.setImage(playerCImage);

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
                playerAImage = orderDice.getDiceRollImages()[0];

                orderDice.roll();
                two = orderDice.getDiceOneResult();
                playerBImage = orderDice.getDiceRollImages()[0];

                orderDice.roll();
                three = orderDice.getDiceOneResult();
                playerCImage = orderDice.getDiceRollImages()[0];

                orderDice.roll();
                four = orderDice.getDiceOneResult();
                playerDImage = orderDice.getDiceRollImages()[0];
                //display correct die image
                dieOne.setImage(playerAImage);
                dieTwo.setImage(playerBImage);
                dieThree.setImage(playerCImage);
                dieFour.setImage(playerDImage);

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

//    private Image die(int rollVal) throws MalformedURLException {
//        return getImage(rollVal);
//    }

//    private static Image[] initializeDiceImageArray() {
//        // store the system path as a string
//        String sysPath = System.getProperty("user.dir");
//        //System.out.println(sysPath);
//        OSValidator osValidator = new OSValidator();
//        String os = osValidator.os;
//        Image[] diceImageArray = null;
//        try {
//            diceImageArray = new Image[6];
//            File oneFile = new File(osValidator.getPathToFile("die1.png", "Resources.Images"));
//            String oneURL = oneFile.toURI().toURL().toString();
//
//            File twoFile = new File(osValidator.getPathToFile("die2.png", "Resources.Images"));
//            String twoURL = twoFile.toURI().toURL().toString();
//
//            File threeFile = new File(osValidator.getPathToFile("die3.png", "Resources.Images"));
//            String threeURL = threeFile.toURI().toURL().toString();
//
//            File fourFile = new File(osValidator.getPathToFile("die4.png", "Resources.Images"));
//            String fourURL = fourFile.toURI().toURL().toString();
//
//            File fiveFile = new File(osValidator.getPathToFile("die5.png", "Resources.Images"));
//            String fiveURL = fiveFile.toURI().toURL().toString();
//
//            File sixFile = new File(osValidator.getPathToFile("die6.png", "Resources.Images"));
//            String sixURL = sixFile.toURI().toURL().toString();
//
//            diceImageArray[0] = new Image(oneURL);
//            diceImageArray[1] = new Image(twoURL);
//            diceImageArray[2] = new Image(threeURL);
//            diceImageArray[3] = new Image(fourURL);
//            diceImageArray[4] = new Image(fiveURL);
//            diceImageArray[6] = new Image(sixURL);
//        } catch(MalformedURLException malformedURLException) {
//            malformedURLException.printStackTrace();
//        }
//        return diceImageArray;
//    }


//    private static Image getImage(int rollVal) throws MalformedURLException {
//
//        switch(rollVal){
//            case 1:
//                return diceImageArray[0];
//            case 2:
//                return diceImageArray[1];
//            case 3:
//                return diceImageArray[2];
//            case 4:
//                return diceImageArray[3];
//            case 5:
//                return diceImageArray[4];
//            default:
//                return diceImageArray[5];
//        }
//    }

    //updates labels, token images, and adds player to player list
    private void labeling(Label label, ImageView imageViewContent, int position) throws MalformedURLException {
//        // store the system path as a string
//        String sysPath = System.getProperty("user.dir");
//        /* Use the OSValidator class to get the right URL path for the working directory */
//        OSValidator osValidator = new OSValidator();
//        String os = osValidator.os;
//        String diePath = null;
//
//        File oneFile = new File(osValidator.getPathToFile("dog.png", "Resources/Images"));
//        String oneURL = oneFile.toURI().toURL().toString();
//
//        File twoFile = new File(osValidator.getPathToFile("shoe.png", "Resources/Images"));
//        String twoURL = twoFile.toURI().toURL().toString();
//
//        File threeFile = new File(osValidator.getPathToFile("racecar.png", "Resources/Images"));
//        String threeURL = threeFile.toURI().toURL().toString();
//
//        File fourFile = new File(osValidator.getPathToFile("thimble.png", "Resources/Images"));
//        String fourURL = fourFile.toURI().toURL().toString();
//
//        Image one = new Image(oneURL);
//        Image two = new Image(twoURL);
//        Image thr = new Image(threeURL);
//        Image fou = new Image(fourURL);
        ImageContainer imgContainer = new ImageContainer();
        Image one = imgContainer.getTokenImage("dog");
        Image two = imgContainer.getTokenImage("shoe");
        Image thr = imgContainer.getTokenImage("reacecar");
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
        this.menuController.switchToGameTab();
    }

}
