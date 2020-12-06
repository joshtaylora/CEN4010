package Models;
/**
 * @author Joshua
 * @version 1.0.1
 */

import Resources.OSValidator;
import javafx.scene.image.Image;

import java.io.File;
import java.net.MalformedURLException;
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

	public Image dieImage1;
	public Image dieImage2;


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

		this.dieImage1 = null;
		this.dieImage2 = null;
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

		this.gameDice = new Dice();
		// while the timer has not run out ...
        //checkWinner();
	}

	/**
	 * Method used to perform the roll for the player
	 */
	public void playerRoll() {
		int spacesToAdvanceToken = 0;
		Player rollingPlayer = this.getCurrentPlayer();

		this.gameDice.roll();

		this.dieImage1 = gameDice.getDiceRollImages()[0];
		this.dieImage2 = gameDice.getDiceRollImages()[1];

		//		check if the player rolled doubles
		if (!this.gameDice.getDoubleResults()) {

			rollingPlayer.setDiceRollResults(this.gameDice.getDiceRollValue());
			rollingPlayer.setRollStatus(true);
		} // else if they roll doubles but haven't rolled enough times for this roll to send them to jail
		else if (rollingPlayer.getDoubles() < 2 && this.gameDice.getDoubleResults()) {
			// increment the Player's counter storing the number of double rolls the player has had in a row
			rollingPlayer.incrementDoubles();
			rollingPlayer.setDiceRollResults(this.gameDice.getDiceRollValue());
			rollingPlayer.setRollStatus(false);

		}
		else if (rollingPlayer.getJailStatus() && this.gameDice.getDoubleResults()) {
			// player is in jail and rolling to try to get out of doubles
			rollingPlayer.setDiceRollResults(this.gameDice.getDiceRollValue());
			rollingPlayer.setJailStatus(false);

		}
		else {
			rollingPlayer.setDiceRollResults(this.gameDice.getDiceRollValue());
			// sets the player's current tile to the jail tile
			if (!rollingPlayer.getJailStatus()) {
				rollingPlayer.setJailStatus(true);
				rollingPlayer.resetDoubles();
			}
			rollingPlayer.setRollStatus(true);
		}
	}

	public boolean checkPlayerRollResults() {
		boolean rollAgain;
		// if the player did NOT roll doubles
		rollAgain = this.gameDice.getDoubleResults();
		return rollAgain;
	}

	/**
	 * Advance the player the number of spaces specified
	 * @param spaces the number of tiles you wish to advance the player
	 */
	public Tile advancePlayerTile(int spaces) {
		this.currentPlayer.setCurrentTile(this.gameBoard.move(this.currentPlayer.getCurrentTile(), spaces));
		return this.currentPlayer.getCurrentTile();
	}



	/**
	 * Method used to retrieve the Player object for the player whose turn it currently is
	 * @return returns the Player whose turn it currently is
	 */
	public Player getCurrentPlayer() {
		return this.currentPlayer;
	}

	/**
	 * Method to retrieve the index in the playerList LinkedList of the currentPlayer
	 * @return the integer value of the current player's index in the playerList LinkedList
	 */
	public int getCurrentPlayerIndex() {
		return this.playerList.indexOf(this.currentPlayer);
	}

	/**
	 * Method to retrieve the index of a specific player
	 * @param player the player whose index you wish to retrieve
	 * @return the integer index of that player in the playerList LinkedList
	 */
	public int getPlayerIndex(Player player) {
		return this.playerList.indexOf(player);
	}

//	====================================================================================================================

	/**
	 * Function used to populate the tokenImages array with the proper image URL and Image objects
	 * @param numPlayers integer specifying the number of players that will need images for their tokens
	 * @return the array of images with the number of images specified by the number of players
	 */
	private static Image[] tokenImageArrayInitializer(int numPlayers) {

		// Grab the working directory string
		String sysPath = System.getProperty("user.dir");
		OSValidator osValidator = new OSValidator();
		String os = osValidator.os;
		String resourcesPath = null;

		// Initialize values to null so that we can reference them outside the scope of the try/catch
		Image[] tokenImages = null;
		// Wrap the image creation in a try/catch block to catch MalformedURLException
		try {

			File dogFile = new File(osValidator.getPathToFile("dog.png","Resources"));
			String dogURL = dogFile.toURI().toURL().toString();
			//System.out.println("tokenImagePath: " + tokenImagePath);

			File shoeFile = new File(osValidator.getPathToFile("shoe.png","Resources"));
			String shoeURL = shoeFile.toURI().toURL().toString();
			//System.out.println("tokenImagePath: " + tokenImagePath);

			File raceCarFile = new File(osValidator.getPathToFile("racecar.png","Resources"));
			String raceCarURL = raceCarFile.toURI().toURL().toString();
			//System.out.println("tokenImagePath: " + tokenImagePath);


			File thimbleFile = new File(osValidator.getPathToFile("racecar.png","Resources"));
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

	public int getCurrentPlayerNumber() {
		return playerList.indexOf(this.getCurrentPlayer());
	}

	/*
	public Tile takeTurnInJail(){
		Tile temp = null;

    	if(gameDice.checkDoubles()){
    		temp = gameBoard.move(currentPlayer.getCurrentTile(), spaces);
    		currentPlayer.setCurrentTile(temp);
    		currentPlayer.setDoubles();
    	}
    	else{
    		currentPlayer.incrementDoubles();
    		if(currentPlayer.getDoubles() == 3){
    			currentPlayer.setAccBalance(currentPlayer.getAccBalance() - 50);
    			temp = gameBoard.move(currentPlayer.getCurrentTile(), spaces);
    			currentPlayer.setCurrentTile(temp);
    			currentPlayer.setDoubles();
    		}
    	}
    	return  temp;
	}
	public void buyProperty(){
		Tile propertyToBuy = this.currentPlayer.getCurrentTile();
		String tileType = propertyToBuy.getType();
		switch (tileType) {
			case "Deed":
				this.currentPlayer.purchaseDeed(propertyToBuy);
				currentTile.setOwner(currentPlayer);
				if (currentPlayer.getPlayerDeeds()[currentTile.getPropertySet()].checkMonopoly()) {
					currentTile.setHouses();
				}
				break;
			case "RailRoad":
				currentTile.setOwner(currentPlayer);
				currentPlayer.increaseRailroads();
				break;
			case "Utility":
				currentTile.setOwner(currentPlayer);
				currentPlayer.increaseUtilities();
				break;
		  }
	}

  public String checkWinner(){
    //Check winner by finding out sum of houses, hotels, properties, and currentMoney
    //propertySum is the method to use
    //make an array to store the property set array
    int sum = 0;
    PropertySet[] tempArray = currentPlayer.getPlayerDeeds();
    String winner = "Player" + this.playerList.indexOf(this.currentPlayer);
    for(int i = 0; i < 9; i++){
      sum = sum + tempArray[i].propertySum();
    }
    int sum1 = 0;
    currentPlayer = this.playerList.get(1 + (this.playerList.indexOf(this.currentPlayer)));
    String temp1 = currentPlayer.name();
    tempArray = currentPlayer.getPlayerDeeds();
    for(int i = 0; i < 9; i++){
      sum1 = sum1 + tempArray[i].propertySum;
    }
    int sum2;
    currentPlayer = currentPlayer.next();
    String temp2 = currentPlayer.name();
    tempArray = currentPlayer.getPlayerDeeds();
    for(int i = 0; i < 9; i++){
      sum2 = sum2 + tempArray[i].propertySum;
    }
    int sum3;
    currentPlayer = currentPlayer.next();
    String temp3 = currentPlayer.name();
    tempArray = currentPlayer.getPlayerDeeds();
    for(int i = 0; i < 9; i++){
      sum3 = sum3 + tempArray[i].propertySum;
    }
    if(sum > sum1){
      if(sum > sum2){
        if(sum > sum3){
          return winner;
        }
      }
    }
    if( sum1 > sum){
      if(sum1 > sum2){
        if(sum1 > sum3){
          return temp1;
        }
      }
    }
    if( sum2 > sum){
      if(sum2 > sum1){
        if(sum2 > sum3){
          return temp2;
        }
      }
    }
    if(sum3 > sum){
      if(sum3 > sum1){
        if(sum3 > sum2){
          return temp3;
        }
      }
    }
  }

 */

//	====================================================================================================================


}