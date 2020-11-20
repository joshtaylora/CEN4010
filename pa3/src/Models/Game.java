package Models;
/**
 * @author Joshua
 * @version 1.0.1
 */

import javafx.scene.image.Image;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.ArrayList;

public class Game {

	// Class variables
    private Player startingPlayer;
    private LinkedList<Player> playerList;
    private int numPlayers;
    private int timeLimit;
    private Board gameBoard;
    private Dice gameDice;
    private Timer gameTimer;
    private ArrayList<Token> tokenList;
    private int initialAccountBalance = 1500;

    private static Image[] tokenImages;
    /**
     * TODO 1) Board needs Tokens to move around the board
     * TODO 2) get a functional model for a turn going and implement it
     */

    /**
     * What should the main menu pass to the game class to instantiate the game object
     * - Timer object
     * - LinkedList of Player objects
     * -
     */


    /** Class Constructor
     * @param numPlayers: retrieved from the main menu view, denotes the number of players that will be playing
     * @param timeLimit: retrieved from the main menu view, denotes the starting value for the timer
     */
    public Game(int numPlayers, int timeLimit) {
    	// assign the number of players
    	this.numPlayers = numPlayers;
    	// initialize to
    	this.timeLimit = timeLimit;
        // initialize the board to a new board object
    	this.gameBoard = new Board();
    	// initialize the player list to an empty linked list of player objects
    	this.playerList = new LinkedList<Player>();
        // set the initial timer to the specified timer value
    	this.gameTimer = new Timer(this.timeLimit);

        // store the images for the tokens in an easily accessible array
        tokenImages[0] = new Image("@../Resources/dog.png");
        tokenImages[1] = new Image("@../Resources/hat.png");
        tokenImages[2] = new Image("@../Resources/racecar.png");
        tokenImages[3] = new Image("@../Resources/thimble.png");

        // for the number of players specified in the main menu, add that many player and corresponding token
        //      objects to the appropriate list
        for (int i = 0; i < this.numPlayers; i++) {
            Player player = new Player(initialAccountBalance,
                    this.gameBoard.searchTile("Go"),
                    propertySetInitializer());
            Token pToken = new Token(player, tokenImages[i]);
            playerList.add(player);
            tokenList.add(pToken);
        }



    }
    /**
     * initializes the property set array for each player
     * @param -> no args needed, function purely used to create & initialize array of property sets
     *              for each player
     */
    private PropertySet[] propertySetInitializer() {
        // initialize all of the property sets that will go in the array
        PropertySet brown = new PropertySet(2);
        PropertySet lightBlue = new PropertySet(3);
        PropertySet pink = new PropertySet(3);
        PropertySet orange = new PropertySet(3);
        PropertySet red = new PropertySet(3);
        PropertySet yellow = new PropertySet(3);
        PropertySet green = new PropertySet(3);
        PropertySet darkBlue = new PropertySet(2);
        PropertySet railRoad = new PropertySet(4);
        PropertySet utility = new PropertySet(2);

        PropertySet[] propertySetArray = {
                brown,
                lightBlue,
                pink,
                orange,
                red,
                yellow,
                green,
                darkBlue,
                railRoad,
                utility
        };
        return propertySetArray;
    }
}
