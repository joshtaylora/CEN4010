package Models;

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
    /**
     * What should the main menu pass to the game class to instantiate the game object
     * - Timer object
     * - LinkedList of Player objects
     * -
     */
    // Class Constructor
    Game(int numPlayers, int timeLimit, ArrayList<Token> tokenList) {
    	// assign the number of players
    	this.numPlayers = numPlayers;
    	this.timeLimit = timeLimit;
    	this.tokenList = tokenList;


    }
}
