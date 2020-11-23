package Models;

/**
 * @author Joshua
 * @version 1.0.1
 */

import javafx.scene.image.Image;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;
import java.util.ArrayList;

public class Game {

	// Class variables
	public Player currentPlayer;
	public LinkedList<Player> playerList;
	public int numPlayers;

	public long timeLimit;
	long startTime;
	long endTime;

	public Board gameBoard;
	public Dice gameDice;
	public long gameTimer;
	public ArrayList<Token> tokenList;
	public int initialAccountBalance = 1500;

	public Image[] tokenImages;



//	====================================================================================================================
	/**
	 * Class Constructor
	 * 
	 * @param numPlayers: retrieved from the main menu view, denotes the number of
	 *                    players that will be playing
	 * @param timeLimit:  retrieved from the main menu view, denotes the starting
	 *                    value for the timer
	 */
	public Game(int numPlayers, int timeLimit) {
		// assign the number of players
		this.numPlayers = numPlayers;
		// initialize the start time to current system time in minutes
		this.startTime = System.currentTimeMillis();
		// initialize to the number of minutes specified * the number of milliseconds in a minute
		int minuteToMilliS = 60000;
		this.timeLimit = timeLimit * minuteToMilliS;

		// this.endTime = this.timeLimit + startTime;

		// initialize the board to a new board object
		this.gameBoard = new Board();
		// initialize the player list to an empty linked list of player objects
		this.playerList = new LinkedList<Player>();
		this.tokenList = new ArrayList<>();
		// set the initial timer to the specified timer value

		// store the images for the tokens in an easily accessible array
		Image[] imgArr = tokenImageArrayInitializer(this.numPlayers);
		// Check if we could successfully grab the token images
		if (imgArr != null) {
			this.tokenImages = new Image[imgArr.length];
			System.arraycopy(this.tokenImages, 0, imgArr, 0, imgArr.length);
		}
		else {
			// if the token image array is null, we need to exit the program and display an error
			System.out.println("ERROR: image URLs for tokens not found, application terminated");
			System.exit(1);
		}
		// Add the appropriate number of players and tokens to their respective lists
		Player player1 = null;
		Player player2 = null;
		Player player3 = null;
		Player player4 = null;

		Token p1Token = null;
		Token p2Token = null;
		Token p3Token = null;
		Token p4Token = null;

		switch(this.numPlayers) {
			case(2):
				player1 = new Player(initialAccountBalance, this.gameBoard.searchTile("GO"), propertySetInitializer());
				playerList.add(player1);
				player2 = new  Player(initialAccountBalance, this.gameBoard.searchTile("GO"), propertySetInitializer());
				playerList.add(player2);
				p1Token = new Token(player1, this.tokenImages[0]);
				tokenList.add(p1Token);
				p2Token = new Token(player2, this.tokenImages[1]);
				tokenList.add(p2Token);
				break;
			case(3):
				player1 = new Player(initialAccountBalance, this.gameBoard.searchTile("GO"), propertySetInitializer());
				playerList.add(player1);
				p1Token = new Token(player1, this.tokenImages[0]);
				tokenList.add(p1Token);
				player2 = new  Player(initialAccountBalance, this.gameBoard.searchTile("GO"), propertySetInitializer());
				playerList.add(player2);
				p2Token = new Token(player2, this.tokenImages[1]);
				tokenList.add(p2Token);
				player3 = new Player(initialAccountBalance, this.gameBoard.searchTile("GO"), propertySetInitializer());
				playerList.add(player3);
				p3Token = new Token(player3, tokenImages[2]);
				tokenList.add(p3Token);
				break;
			case(4):
				player1 = new Player(initialAccountBalance, this.gameBoard.searchTile("GO"), propertySetInitializer());
				playerList.add(player1);
				p1Token = new Token(player1, this.tokenImages[0]);
				tokenList.add(p1Token);
				player2 = new  Player(initialAccountBalance, this.gameBoard.searchTile("GO"), propertySetInitializer());
				playerList.add(player2);
				p2Token = new Token(player2, this.tokenImages[1]);
				tokenList.add(p2Token);
				player3 = new Player(initialAccountBalance, this.gameBoard.searchTile("GO"), propertySetInitializer());
				playerList.add(player3);
				p3Token = new Token(player3, tokenImages[2]);
				tokenList.add(p3Token);
				player4 = new Player(initialAccountBalance, this.gameBoard.searchTile("GO"), propertySetInitializer());
				playerList.add(player4);
				p4Token = new Token(player4, tokenImages[3]);
				tokenList.add(p4Token);
				break;
			default:
				System.out.println("ERROR: Undefined number of players encountered. System terminating.");
				System.exit(1);
		}
		this.currentPlayer = playerList.get(0);
		// NEED TO ADD INITIAL ROLL FOR 1st PLAYER ORDER
		/*
		 * Roll for each player to determine first player for each player, roll set max
		 * roll = first roll, - > algo to check who rolled highest set currentPlayer
		 * initially to the index for loop condition and then just increment after that
		 * for each turn
		 */

		Dice gameDice = new Dice();
		this.gameDice = gameDice;
		// while the timer has not run out ...
		while (System.currentTimeMillis() < this.endTime) {
			for (int i = 0; i < this.numPlayers; i++) {
				int playerRoll = gameDice.roll();
				System.out.println("Player rolled: " + playerRoll);
			}
		}

	}

//	====================================================================================================================

	public Player getCurrentPlayer() {
		return this.currentPlayer;
	}
//	====================================================================================================================

	public static Image[] tokenImageArrayInitializer(int numPlayers) {

		// Grab the working directory string
		String sysPath = System.getProperty("user.dir");
		String tokenImagePath = sysPath.concat("\\src\\Resources\\");
		// Initialize values to null so that we can reference them outside the scope of the try/catch
		Image[] tokenImages = null;
		// Wrap the image creation in a try/catch block to catch MalformedURLException
		try {

			File dogFile = new File(tokenImagePath.concat("dog.png"));
			String dogURL = dogFile.toURI().toURL().toString();
			//System.out.println("tokenImagePath: " + tokenImagePath);

			File shoeFile = new File(tokenImagePath.concat("shoe.png"));
			String shoeURL = shoeFile.toURI().toURL().toString();
			//System.out.println("tokenImagePath: " + tokenImagePath);

			File raceCarFile = new File(tokenImagePath.concat("racecar.png"));
			String raceCarURL = raceCarFile.toURI().toURL().toString();
			//System.out.println("tokenImagePath: " + tokenImagePath);

			File thimbleFile = new File(tokenImagePath.concat("thimble.png"));
			String thimbleURL = thimbleFile.toURI().toURL().toString();
			//System.out.println("tokenImagePath: " + tokenImagePath);

			// initialize the tokenImages array to the number of players playing and if an error occurs and no players
			// are in the game, initialize it to null
			switch(numPlayers) {
				case(2):
					tokenImages = new Image[2];
					tokenImages[0] = new Image(dogURL);
					tokenImages[1] = new Image(shoeURL);
					break;
				case(3):
					tokenImages = new Image[3];
					tokenImages[0] = new Image(dogURL);
					tokenImages[1] = new Image(shoeURL);
					tokenImages[2] = new Image(raceCarURL);
					break;
				case(4):
					tokenImages = new Image[4];
					//System.out.println("tokenImages.length: " + tokenImages.length);
					tokenImages[0] = new Image(dogURL);
					//System.out.println("Path to tokenImages[0]: " + dogURL);
					tokenImages[1] = new Image(shoeURL);
					//System.out.println("Path to tokenImages[1]: " + shoeURL);
					tokenImages[2] = new Image(raceCarURL);
					//System.out.println("Path to tokenImages[2]: " + raceCarURL);
					tokenImages[3] = new Image(thimbleURL);
					//System.out.println("Path to tokenImages[3]: " + thimbleURL);
					break;
				default:
					tokenImages = null;
			}
		} catch(MalformedURLException malformedE) {
			System.out.println("ERROR: image URLs for token images not found, system terminating");
			System.exit(1);
		}
		return tokenImages;

	}

//	====================================================================================================================

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

		return new PropertySet[]{ brown, lightBlue, pink, orange, red, yellow, green, darkBlue, railRoad,
				utility };
	}
//	GETTER METHODS FOR PRIVATE CLASS VARIABLES
	public int getNumPlayers() {
		return numPlayers;
	}

}
