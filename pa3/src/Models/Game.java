package Models;
/*
 * @author Joshua
 * @version 1.0.1
 */

import Resources.ImageContainer;
import javafx.scene.image.Image;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.ArrayList;

public class Game {

	// Class variables
	public Player currentPlayer;
	private LinkedList<Player> playerList;
	public int numPlayers;

	public long timeLimit;
	long startTime;
	long endTime;

	public Board gameBoard;
	public Dice gameDice;
	public ArrayList<Token> tokenList;
	public int initialAccountBalance = 1500;

	public Image[] tokenImages;

	public Image dieImage1;
	public Image dieImage2;


	public static ImageContainer imgContainer = new ImageContainer();

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
		this.timeLimit = (long) timeLimit * minuteToMilliS;
		this.endTime = this.timeLimit + startTime;

		// initialize the board to a new board object
		this.gameBoard = new Board();
		// initialize the player list to an empty linked list of player objects
		this.playerList = new LinkedList<Player>();
		this.tokenList = new ArrayList<>();

		this.dieImage1 = null;
		this.dieImage2 = null;
		// set the initial timer to the specified timer value

		//store the images for the tokens in an easily accessible array
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
				player1 = new Player("player1", initialAccountBalance, this.gameBoard.searchTile("GO"), propertySetInitializer());
				playerList.add(player1);
				player2 = new  Player("player2", initialAccountBalance, this.gameBoard.searchTile("GO"), propertySetInitializer());
				playerList.add(player2);
				p1Token = new Token(player1, this.tokenImages[0]);
				tokenList.add(p1Token);
				p2Token = new Token(player2, this.tokenImages[1]);
				tokenList.add(p2Token);
				break;
			case(3):
				player1 = new Player("player1", initialAccountBalance, this.gameBoard.searchTile("GO"), propertySetInitializer());
				playerList.add(player1);
				p1Token = new Token(player1, this.tokenImages[0]);
				tokenList.add(p1Token);
				player2 = new  Player("player2", initialAccountBalance, this.gameBoard.searchTile("GO"), propertySetInitializer());
				playerList.add(player2);
				p2Token = new Token(player2, this.tokenImages[1]);
				tokenList.add(p2Token);
				player3 = new  Player("player3", initialAccountBalance, this.gameBoard.searchTile("GO"), propertySetInitializer());
				playerList.add(player3);
				p3Token = new Token(player3, tokenImages[2]);
				tokenList.add(p3Token);
				break;
			case(4):
				player1 = new  Player("player1", initialAccountBalance, this.gameBoard.searchTile("GO"), propertySetInitializer());
				playerList.add(player1);
				p1Token = new Token(player1, this.tokenImages[0]);
				tokenList.add(p1Token);
				player2 = new  Player("player2", initialAccountBalance, this.gameBoard.searchTile("GO"), propertySetInitializer());
				playerList.add(player2);
				p2Token = new Token(player2, this.tokenImages[1]);
				tokenList.add(p2Token);
				player3 = new  Player("player3", initialAccountBalance, this.gameBoard.searchTile("GO"), propertySetInitializer());
				playerList.add(player3);
				p3Token = new Token(player3, tokenImages[2]);
				tokenList.add(p3Token);
				player4 = new  Player("player4", initialAccountBalance, this.gameBoard.searchTile("GO"), propertySetInitializer());
				playerList.add(player4);
				p4Token = new Token(player4, tokenImages[3]);
				tokenList.add(p4Token);
				break;
			default:
				System.out.println("ERROR: Undefined number of players encountered. System terminating.");
				System.exit(1);
		}
		this.currentPlayer = playerList.get(0);

		this.gameDice = new Dice();
	}

	/**
	 * Method used to perform the roll for the player
	 */
	public int playerRoll() {
		Player rollingPlayer = this.getCurrentPlayer();
		boolean js = rollingPlayer.getJailStatus();
		int numDoubles;
		this.gameDice.roll();

		this.dieImage1 = imgContainer.getDieImage(gameDice.getDiceOneResult());
		this.dieImage2 = imgContainer.getDieImage(gameDice.getDiceTwoResult());

		//if: player is currently NOT in jail===========================================================================NOT IN JAIL
		if(!js) {
			//if: player HAS NOT rolled doubles
			if (!this.gameDice.getDoubleResults()) {
				rollingPlayer.resetDoubles();
				advancePlayerTile(this.gameDice.getDiceRollValue());
				//if: player lands on "Go to Jail"
				if(rollingPlayer.getCurrentTile().getName().equals("Go To Jail")){
					rollingPlayer.resetDoubles();
					rollingPlayer.setCurrentTile(gameBoard.searchTile("Jail/Just Visiting"));
					rollingPlayer.setJailStatus(true);
				}
				rollingPlayer.setRollStatus(true);
			}
			//else: player HAS rolled doubles
			else{
				rollingPlayer.incrementDoubles();
				numDoubles = rollingPlayer.getDoubles();
				//if: player has rolled a safe double, so move to corresponding space and give another turn
				if(numDoubles < 3){
					advancePlayerTile(this.gameDice.getDiceRollValue());
					//if: player lands on "Go to Jail"
					if(rollingPlayer.getCurrentTile().getName().equals("Go To Jail")){
						rollingPlayer.resetDoubles();
						rollingPlayer.setCurrentTile(gameBoard.searchTile("Jail/Just Visiting"));
						rollingPlayer.setJailStatus(true);
						rollingPlayer.setRollStatus(true);
					}
					else {
						rollingPlayer.setRollStatus(false);
					}
				}
				//if: player has rolled a jail double, so move them to jail and end turn
				else if(numDoubles == 3){
					rollingPlayer.resetDoubles();
					rollingPlayer.setCurrentTile(gameBoard.searchTile("Jail/Just Visiting"));
					rollingPlayer.setJailStatus(true);
					rollingPlayer.setRollStatus(true);
				}
			}
		}
		//else: player is currently IN jail=============================================================================IS IN JAIL
		else{
			//if: player HAS NOT rolled doubles
			if (!this.gameDice.getDoubleResults()) {
				rollingPlayer.incrementDoubles();
				numDoubles = rollingPlayer.getDoubles();
				//if: player has not rolled enough doubles to leave jail, stay in jail and end turn
				if(numDoubles < 3){
					advancePlayerTile(0);
					rollingPlayer.setRollStatus(true);
				}
				//if: player has used all chances to roll doubles, subtract money, move them out of jail
				else if(numDoubles == 3){
					rollingPlayer.resetDoubles();
					rollingPlayer.setAccBalance(rollingPlayer.getAccBalance() - 50);
					advancePlayerTile(this.gameDice.getDiceRollValue());
					rollingPlayer.setJailStatus(false);
					rollingPlayer.setRollStatus(true);
				}

			}
			//else: player HAS rolled doubles
			else{
				rollingPlayer.resetDoubles();
				advancePlayerTile(this.gameDice.getDiceRollValue());
				rollingPlayer.setRollStatus(true);
			}
		}
		return this.gameDice.getDiceRollValue();
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

	/**
	 * Method to get a player from the player list
	 * @param playerNum the number of the player that should be retrieved i.e 1, 2, 3, or 4
	 * @return the player object at the specified index
	 */
	public Player getPlayerObject(int playerNum) {
		Player retPlayer = null;
		// ensure that the indexed specified is within the range of the playerlist
		if ((playerNum <= playerList.size()) && (playerNum > 0)) {
			retPlayer = playerList.get(playerNum - 1);
		}
		else {
			System.out.println("ERROR in method getPlayer of Game class, player number queried is not within the scope of the player list");
			System.exit(1);
		}
		return retPlayer;
	}
//	====================================================================================================================

	/**
	 * Function used to populate the tokenImages array with the proper image URL and Image objects
	 * @param numPlayers integer specifying the number of players that will need images for their tokens
	 * @return the array of images with the number of images specified by the number of players
	 */
	private static Image[] tokenImageArrayInitializer(int numPlayers) {
		// Initialize values to null so that we can reference them outside the scope of the try/catch
		Image[] tokenImages = null;
		// Wrap the image creation in a try/catch block to catch MalformedURLException


		// initialize the tokenImages array to the number of players playing and if an error occurs and no players
		// are in the game, initialize it to null
		switch(numPlayers) {
			case(2):
				tokenImages = new Image[2];
				tokenImages[0] = imgContainer.getTokenImage("dog");
				tokenImages[1] = imgContainer.getTokenImage("shoe");
				break;
			case(3):
				tokenImages = new Image[3];
				tokenImages[0] = imgContainer.getTokenImage("dog");
				tokenImages[1] = imgContainer.getTokenImage("shoe");
				tokenImages[2] = imgContainer.getTokenImage("racecar");
				break;
			case(4):
				tokenImages = new Image[4];
				tokenImages[0] = imgContainer.getTokenImage("dog");
				tokenImages[1] = imgContainer.getTokenImage("shoe");
				tokenImages[2] = imgContainer.getTokenImage("racecar");
				tokenImages[3] = imgContainer.getTokenImage("thimble");
				break;
			default:
				break;
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


	/**
	 * Function called by the GameController class after a player has clicked the End Turn button
	 */
	public void setNextPlayer() {
		int currentPlayerIndex = getCurrentPlayerIndex();
		if (currentPlayerIndex < this.numPlayers - 1) {
			this.currentPlayer = playerList.get(currentPlayerIndex+1);
		}
		else {
			this.currentPlayer = playerList.get(0);
		}
	}

	/**
	 *
	 * @param rollingPlayer the player who is taking their turn currently
	 * @return ownership of a tile, where
	 * 0= no one owns this, 1=currentPlayer owns this, 2=another player owns this
	 */
	public int checkOwnership(Player rollingPlayer){
		if(rollingPlayer.getCurrentTile().getType().equals("Deed")){
			Deed obj = (Deed) rollingPlayer.getCurrentTile();
			Player tileOwner = obj.getOwner();
			if(tileOwner == null){
				return 0;
			}
			else if(tileOwner.getName().equals(rollingPlayer.getName())){
				return 1;
			}
			else{
				return 2;
			}
		}
		else if(rollingPlayer.getCurrentTile().getType().equals("RailRoad")){
			RailRoad obj = (RailRoad) rollingPlayer.getCurrentTile();
			Player check = obj.getOwner();
			if(check == null){
				return 0;
			}
			else if(check.getName().equals(rollingPlayer.getName())){
				return 1;
			}
			else{
				return 2;
			}
		}
		else if(rollingPlayer.getCurrentTile().getType().equals("Utility")){
			Utility obj = (Utility) rollingPlayer.getCurrentTile();
			Player check = obj.getOwner();
			if(check == null){
				return 0;
			}
			else if(check.getName().equals(rollingPlayer.getName())){
				return 1;
			}
			else{
				return 2;
			}
		}
		return 0;
	}

	/**
	 *
	 * @return true if there is time left in the game, false if the time is up
	 */
	public boolean isThereTimeLeft() {
		if (endTime > System.currentTimeMillis()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 *
	 * @returns the index of the winning player. If multiple players have the max net worth,
	 * the first player in the list with the max net worth wins
	 */
	public int checkWinner(){
		//index of player with greatest net worth
		int maxIndex = 0;
		//array of player's net worth
		Integer[] sums = {0, 0, 0, 0};
		int index =  0;

		//calculate net worth of each player and store it in sum array
		for (Player player: playerList) {
			sums[index] = player.calcNetWorth();
			index++;
		}

		int max = Collections.max(Arrays.asList(sums));
		for (int i: sums) {
			if(max > i){
				maxIndex++;
			}
			else
				break;
		}

		return maxIndex;
	}



//	====================================================================================================================


}