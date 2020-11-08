package Models;

import java.util.ArrayList;
import java.util.LinkedList;

public class Game {
	
	// Class variables
    Player startingPlayer;
    LinkedList<Player> playerList;
    int numPlayers;
    int timeLimit;
    Board gameBoard;
    Dice gameDice;
    Timer gameTimer;
    ArrayList<Token> tokenList;
    
    // Class Constructor
    Game(int numPlayers, int timeLimit, ArrayList<Token> tokenList) {
    	// assign the number of players
    	this.numPlayers = numPlayers;
    	this.timeLimit = timeLimit;
    	this.tokenList = tokenList;
    	
    	
    }

}
