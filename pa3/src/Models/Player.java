package Models;

/**
 * @author Elisha
 * @version 11/5/20
 */
public class Player{

	// the variables that are part of the Player class
	private int account;
	private Token playerToken;
	public PropertySet[] playerDeeds;
	private Tile currentTile;
	private boolean inJail;
	private int numRailroads;
	private int numUtilities;
	private int doubles;
	private boolean rollStatus;
	private int diceRollResults;
	private String playerName;

	/**
	 * The constructor for the player class. Takes in Token, Tile to help setup an
	 * initial player. every player will always start with 1500, so might as well
	 * start them there. There are 10 different sets so start that with an empty set
	 * of 10 they shouldn't start in jail. and they don't have any doubles yet.
	 *
	 * @param initial the amount of money to initialize the player's account with
	 * @param currentTile the tile that the player will start on
	 * @param playerDeeds an array of property sets that are initially empty but initialized to hold the correct number
	 *                    of deeds
	 */
	public Player(int initial, Tile currentTile, PropertySet[] playerDeeds, String name) {
		account = initial;
		this.playerDeeds = playerDeeds;
		this.currentTile = currentTile;
		this.playerName = name;
		inJail = false;
		numRailroads = 0;
		numUtilities = 0;
		doubles = 0;
		rollStatus = false;
		diceRollResults = 0;
	}
	
	/**
	 * Takes in a tile, determines its property type, and sets ownership
	 *
	 * @param tile the tile being purchased
	 */
	public void purchaseProperty(Tile tile) {
		String type = tile.getType();
		switch (type) {
			case "Deed":
				Deed obj = (Deed) tile;
				playerDeeds[obj.getPropertySet()].addProperty(obj);
				obj.setOwner(this);
				//checks if player has monopoly and if the monopoly was already checked
				if((this.getPlayerDeeds()[obj.getPropertySet()].checkMonopoly()) && (obj.getHouses() < 1)){
					obj.setHouses();
				}
				break;
			case "RailRoad":
				RailRoad rr = (RailRoad) tile;
				rr.setOwner(this);
				increaseRailroads();
				break;
			case "Utility":
				Utility util = (Utility) tile;
				util.setOwner(this);
				increaseUtilities();
				break;
			default:
				break;
		}
	}

	
	/**
	 * this method is to initiate a trade to another player, trading properties, money or both. Starts off by prompting the player for the object of the
	 * player they would like to trade with. After that the current players properties and money will pull up and they can choose what they want to trade.
	 * After that the other players properties and money will pop up and the current player then chooses what they want to trade in return. Then all the
	 * trade information will be sent to the other player using a method through the Controller. They the other player either accepts or rejects.
	 * Upon accepting the method will transfer all properties and money. Upon rejection the method will just return.
	 * @param tradePlayer
	 * @param cPlayerMoney
	 * @param tPlayerMoney
	 * @param cPlayerDeeds
	 * @param tPlayerDeeds
	 */
	public void performTrade(Player tradePlayer, int cPlayerMoney, int tPlayerMoney, Deed[] cPlayerDeeds, Deed[] tPlayerDeeds, boolean tradeResults) {
		int i;
		int cDeedsSize = cPlayerDeeds.length;
		int tDeedsSize = tPlayerDeeds.length;
		int tAccount = tradePlayer.getAccBalance();

		if (tradeResults) {
			account += tPlayerMoney;
			account -= cPlayerMoney;
			tAccount += cPlayerMoney;
			tAccount -= tPlayerMoney;
			tradePlayer.setAccBalance(tAccount);

			//removing the properties from currentPlayer and adding them to tradePlayer
			for (i = 0; i < cDeedsSize; i++) {
				this.playerDeeds[cPlayerDeeds[i].getPropertySet()].removeProperty(cPlayerDeeds[i]);
				tradePlayer.playerDeeds[cPlayerDeeds[i].getPropertySet()].addProperty(cPlayerDeeds[i]);
			}
			//removing the properties from tradePlayer and adding them to currentPlayer
			for (i = 0; i < cDeedsSize; i++) {
				tradePlayer.playerDeeds[tPlayerDeeds[i].getPropertySet()].removeProperty(tPlayerDeeds[i]);
				this.playerDeeds[tPlayerDeeds[i].getPropertySet()].addProperty(tPlayerDeeds[i]);
			}
		}
	}

	public String getName(){
		return playerName;
	}
	/**
	 * basic get method for the doubles variable
	 * @return int doubles
	 */
	public int getDoubles() {

		return doubles;
	}

	/**
	 * basic set method for the doubles variable
	 */
	public void resetDoubles() {
		doubles = 0;
	}

	public void incrementDoubles() {
		doubles++;
	}

	/**
	 * returns the current number of railroads the player has
	 * @return int
	 */
	public int getNumRailroads() {
		
		return numRailroads;
	}
	
	/**
	 * returns the current number of utilities the player has
	 * @return int
	 */
	public int getNumUtilities() {
		
		return numUtilities;
	}
	
	/**
	 * when called increments the number of railroads by 1
	 */
	public void increaseRailroads() {
		numRailroads++;
		
		return;
	}
	
	/**
	 * when called decrements the number of railroads by 1
	 */
	public void decreaseRailroads() {
		numRailroads--;
		
		return;
	}
	
	/**
	 * when called increments the number of utilities by 1
	 */
	public void increaseUtilities() {
		numUtilities++;
	}
	
	/**
	 * when called decrements the number of utilities by 1
	 */
	public void decreaseUtilities() {
		numUtilities--;
	}
	
	/**
	 *  Simply returns the value of the player's account
	 * @return int
	 */
	public int getAccBalance() {
		return account;
	}
	
	/**
	 * Sets the player's account to balance
	 * @param balance
	 */
	public void setAccBalance(int balance) {
		account = balance;
	}
	
	/**
	 * simply returns the value of inJail as the jail status
	 * @return boolean
	 */
	public boolean getJailStatus() {
		return inJail;
	}
	
	/**
	 * Sets the player's jailstatus to status param
	 * @param status
	 */
	public void setJailStatus(boolean status) {
		inJail = status;
	}
	
	/**
	 * returns the player's token
	 * @return Token
	 */
	public Token getPlayerToken() {
		return playerToken;
	}
	
	/**
	 * sets the player's token using the tok param
	 * @param tok
	 */
	public void setPlayerToken(Token tok) {
		playerToken = tok;
	}
	
	/**
	 * returns the current PropertySet of the player
	 * @return PropertySet[]
	 */
	public PropertySet[] getPlayerDeeds() {
		return playerDeeds;
	}
	
	/**
	 * sets the player's PropertySet using the deeds param
	 * @param deeds
	 */
	public void setPlayerDeeds(PropertySet[] deeds) {
		playerDeeds = deeds;
	}
	
	/**
	 * returns the currentTile the player is on
	 * @return Tile
	 */
	public Tile getCurrentTile() {
		return currentTile;
	}
	
	/**
	 * sets the location of the player to the Tile in params
	 * @param spot
	 */
	public void setCurrentTile(Tile spot) {
		currentTile = spot;
	}

	/**
	 * Josh added 11/24/2020
	 * Method to set the rollstatus variable used to check if the player has rolled yet
	 * @param rollStatus a boolean that specifies if the player has rolled this turn yet
	 */
	public void setRollStatus(boolean rollStatus) {
		this.rollStatus = rollStatus;
	}

	/**
	 * Josh added 11/24/2020
	 * Public method used to check if the player has rolled yet this turn
	 * @return boolean signifying if the player has rolled this turn yet or not
	 */
	public boolean getRollStatus() {
		return this.rollStatus;
	}

	/**
	 * Josh added 11/25/2020 @7:43PM
	 * @param diceRollResults the number of tiles to add to the counter storing the number of tiles that the player
	 *                            will advance after their last roll for the current turn
	 */
	public void setDiceRollResults(int diceRollResults) {
		this.diceRollResults = diceRollResults;
	}

	/**
	 * Josh added 11/25/2020 @ 7:45PM
	 * method to retrieve the number of tiles that the player will advance this turn
	 * @return the total number of tiles that the player's dice rolls this turn will advance them
	 */
	public int getDiceRollResults() {
		return this.diceRollResults;
	}
}
