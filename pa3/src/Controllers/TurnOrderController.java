package Controllers;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.MalformedURLException;

public class TurnOrderController {

// =====================================================================================================================
// ====================================== FXML elements for the Turn Order tab =========================================

    @FXML Tab turnOrderTab; // the tab for the turn order view

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

    private int numberOfPlayers;

    private MenuController menuController;

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
        Models.Dice order = new Models.Dice ();

        //roll dice relevant to number of players
        int one;
        int two;
        int three;
        int four;
        // switch on the numberOfPlayers global var that we got from parsing the choice box's input
        switch(this.numberOfPlayers) {
            case 2:
                one = order.roll()/2;
                two = order.roll()/2;

                //display correct die image
                dieOne.setImage(die(one));
                dieTwo.setImage(die(two));

                //display player order on labels
                if(one>=two){
                    labelOne.setText("Player 1");
                    labelTwo.setText("Player 2");
                }
                else{
                    labelOne.setText("Player 2");
                    labelTwo.setText("Player 1");
                }
                break;
            case 3:
                one = order.roll()/2;
                two = order.roll()/2;
                three = order.roll()/2;

                //display correct die image
                dieOne.setImage(die(one));
                dieTwo.setImage(die(two));
                dieThree.setImage(die(three));

                //determine player order, then update labels
                int[] sort3 = {3, 3, 3};
                int[] comp3 = {one, two, three};
                int[] sorted3 = sortOrder(sort3, comp3);
                labeling(labelOne, sorted3[0]);
                labeling(labelTwo, sorted3[1]);
                labeling(labelThree, sorted3[2]);

                break;
            case 4:
                one = order.roll()/2;
                two = order.roll()/2;
                three = order.roll()/2;
                four = order.roll()/2;

                //display correct die image
                dieOne.setImage(die(one));
                dieTwo.setImage(die(two));
                dieThree.setImage(die(three));
                dieFour.setImage(die(four));

                //determine player order, then update labels
                int[] sort4 = {4, 4, 4, 4};
                int[] comp4 = {one, two, three, four};
                int[] sorted4 = sortOrder(sort4, comp4);
                labeling(labelOne, sorted4[0]);
                labeling(labelTwo, sorted4[1]);
                labeling(labelThree, sorted4[2]);
                labeling(labelFour, sorted4[3]);

                break;
            default:
                break;
        }

    }

    /**
     * Helper function that is used to sort the order of players based on their dice roll values
     * @param sort an area containing the max value for the order that a player can be in based on their roll.
     *             Will be decremented as the roll is compared to the other player's values
     * @param comp The roll values for each player. This array is used to determine the order of the players.
     * @return we return the sorted array containing the proper order for the players
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

    private Image die(int rollVal) throws MalformedURLException {
        return getImage(rollVal);
    }

    private static Image getImage(int rollVal) throws MalformedURLException {
        // store the system path as a string
        String sysPath = System.getProperty("user.dir");
        //System.out.println(sysPath);
        String diePath = sysPath.concat("\\src\\Resources\\");

        File oneFile = new File(diePath.concat("die1.png"));
        String oneURL = oneFile.toURI().toURL().toString();

        File twoFile = new File(diePath.concat("die2.png"));
        String twoURL = twoFile.toURI().toURL().toString();

        File threeFile = new File(diePath.concat("die3.png"));
        String threeURL = threeFile.toURI().toURL().toString();

        File fourFile = new File(diePath.concat("die4.png"));
        String fourURL = fourFile.toURI().toURL().toString();

        File fiveFile = new File(diePath.concat("die5.png"));
        String fiveURL = fiveFile.toURI().toURL().toString();

        File sixFile = new File(diePath.concat("die6.png"));
        String sixURL = sixFile.toURI().toURL().toString();

        Image one = new Image(oneURL);
        Image two = new Image(twoURL);
        Image thr = new Image(threeURL);
        Image fou = new Image(fourURL);
        Image fiv = new Image(fiveURL);
        Image six = new Image(sixURL);
        switch(rollVal){
            case 1:
                return one;
            case 2:
                return two;
            case 3:
                return thr;
            case 4:
                return fou;
            case 5:
                return fiv;
            default:
                return six;
        }
    }

    private void labeling(Label label, int position) {
        switch (position) {
            case 1:
                label.setText("Player 1");
                break;
            case 2:
                label.setText("Player 2");
                break;
            case 3:
                label.setText("Player 3");
                break;
            case 4:
                label.setText("Player 4");
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
