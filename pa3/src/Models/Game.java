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
     * What should the main menu pass to the game class to instantiate the game object
     * - Timer object
     * - LinkedList of Player objects
     * -
     */
    // Class Constructor
    public Game(int numPlayers, int timeLimit) {
    	// assign the number of players
    	this.numPlayers = numPlayers;
    	this.timeLimit = timeLimit;
    	// TODO need to add initializer for tokenList
    	this.gameBoard = new Board();
    	this.playerList = new LinkedList<Player>();
    	this.gameTimer = new Timer(this.timeLimit);

        tokenImages[0] = new Image("@../Resources/dog.png");
        tokenImages[1] = new Image("@../Resources/hat.png");
        tokenImages[2] = new Image("@../Resources/racecar.png");
        tokenImages[3] = new Image("@../Resources/thimble.png");

        switch(this.numPlayers) {
            case(2):
                Player player1 = new Player(initialAccountBalance,
                        this.gameBoard.searchTile("Go"),
                        propertySetInitializer(),

                        );
        }

    }
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
