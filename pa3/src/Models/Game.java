package Models;
/**
 * @author Joshua
 * @version 1.0.1
 */

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
    public Game(int numPlayers, int timeLimit) {
    	// assign the number of players
    	this.numPlayers = numPlayers;
    	this.timeLimit = timeLimit;
    	// TODO need to add initializer for tokenList
    	this.gameBoard = new Board();
    	this.playerList = new LinkedList<Player>();




    }
}
