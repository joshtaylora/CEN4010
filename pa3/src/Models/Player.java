package Models;

/**
 * @author Elisha
 * @version 11/5/20
 */
public class Player {
	
	//the variables that are part of the Player class
	public int account;
	private Token playerToken;
	public PropertySet[] playerDeeds;
	private Tile currentTile;
	private boolean inJail;
	private int numRailroads;
	private int numUtilities;

	/**
	 * The constructor for the player class. Takes in Token, Tile to help setup an initial player.
	 * every player will always start with 1500, so might as well start them there.
	 * There are 10 different sets so start that with an empty set of 10
	 * they shouldn't start in jail.
	 * and they don't have any doubles yet.
	 * @param initial
	 * @param playerToken
	 * @param currentTile
	 * @param playerDeeds
	 */
	public Player(int initial, Token playerToken, Tile currentTile, PropertySet[] playerDeeds) {
		account = initial;
		this.playerToken = playerToken;
		this.playerDeeds = playerDeeds;
		this.currentTile = currentTile;
		inJail = false;
		numRailroads = 0;
		numUtilities = 0;
	}
	
	/**
	 * This program currently takes in a Deed and removes its cost from the player's account and then adds
	 * the property to their playerDeeds array.
	 * then returns the Deed
	 * @param property
	 */
	public void purchaseDeed(Deed property) {
		int cost = property.getPrice();
		account -= cost;
		playerDeeds[property.getPropertySet()].addProperty(property); //we need some way of knowing which set is which i.e SOMEVALUE
	
		return;	
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
	public void initiateTrade(Player tradePlayer, int cPlayerMoney, int tPlayerMoney, Deed cPlayerDeeds[], Deed tPlayerDeeds[], boolean tradeResults) {
		int i;
		int cDeedsSize = cPlayerDeeds.length;
		int tDeedsSize = tPlayerDeeds.length;

		if (tradeResults) {
			account += tPlayerMoney;
			account -= cPlayerMoney;
			tradePlayer.account += cPlayerMoney;
			tradePlayer.account -= tPlayerMoney;
			
			//removing the properties from currentPlayer and adding them to tradePlayer
			for (i = 0; i < cDeedsSize; i++) {
				
			}
		}
		else {
			return;
		}
	}
	
	/**
	 * This method is to receive a trade from another player through th controller, be it properties, money or both. Takes in the result from the controller
	 * and returns the response back through.
	 * @param controllerResponse
	 * @return boolean true/false
	 */
	public boolean receiveTrade(boolean controllerResponse) {
		
		if(controllerResponse) {
			return true;
		}
		else {
			return false;
		}
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
		
		return;
	}
	
	/**
	 * when called decrements the number of utilities by 1
	 */
	public void decreaseUtilities() {
		numUtilities--;
		
		return;
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
		
		return;
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
		
		return;
	}
	
	/**
	 * returns the player's token
	 * @return Token
	 */
	public Token getPlayerToken() {
		
		return playerToken;
	}

	/*
	 * -------------------------------------------------------------------------------
	 * JOSH COMMENT BELOW
	 * @TODO REMOVE this method because the token is attached to a player and th
	 * -------------------------------------------------------------------------------
	 */

	/**
	 * sets the player's token using the tok param
	 * @param tok
	 */
	public void setPlayerToken(Token tok) {
		playerToken = tok;
	
		return;
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
		
		return;
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
		
		return;
	}
}
