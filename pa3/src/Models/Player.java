/**
 * 
 */

/**
 * @author Elisha
 * @version 11/5/20
 */
public class Player {
	static final int INITIALMONEY = 1500; //players always start with 1500 cash
	
	//the variables that are part of the Player class
	private int account;
	private Token playerToken;
	private PropertySet[] playerDeeds;
	private Tile currentTile;
	private boolean inJail;
	//private int numDoubleRolls;		REMOVE THIS
	
	/**
	 * The constructor for the player class. Takes in Token, Tile to help setup an initial player.
	 * every player will always start with 1500, so might as well start them there.
	 * There are 10 different sets so start that with an empty set of 10
	 * they shouldn't start in jail.
	 * and they dont have any doubles yet.
	 */
	public Player(Token playerToken, Tile currentTile, PropertySet[] playerDeeds) {
		account = INITIALMONEY;
		this.playerToken = playerToken;
		this.playerDeeds = playerDeeds;
		this.currentTile = currentTile;
		inJail = false;
		//this.numDoubleRolls = 0;		REMOVE THIS
	}
	
	/**
	 * This program currently takes in a Deed and removes its cost from the player's account and then adds
	 * the property to their playerDeeds array.
	 * then returns the Deed
	 */
	public void purchaseDeed(Deed property) {
		int cost = property.getPrice();
		account = account - cost;
		playerDeeds[property.getPropertySet()].addProperty(property); //we need some way of knowing which set is which i.e SOMEVALUE
	
		return;	
	}
	
	/**
	 * this method is to initiate a trade to another player, trading properties, money or both.
	 */
	public void initiateTrade(Player other) {
		
	}
	
	
	/**
	 * This method is to receive a trade from another player, be it properties, money or both.
	 */
	public boolean receiveTrade(Deed[] deedOffered, Deed[] deedWanted, int moneyOffered, int moneyWanted) {
		/*
		 * TODO somehow display all of this information for the Player to choose if they like the deal or not
		 */
		
		if(/*Player accepts the deal through a button click*/) {
			return true;
		}
		else if(/*Player declines the deal through a decline button click*/) {
			return false;
		}
	}
	
	
	/**
	 * simply returns the value of account as the balance
	 */
	public int getBalance() {
		
		return account;
	}
	
	/**
	 * simply returns the value of inJail as the jail status
	 */
	public boolean getJailStatus() {
		
		return inJail;
	}
	
	/*public int getNumDoubleRolls() { //		REMOVE
		
		return this.numDoubleRolls;
	}
	
	public void setNumDoubleRolls(boolean numRoll) {
		if (numRoll) {
			this.numDoubleRolls++;
		}
		else {
			this.numDoubleRolls = 0;
		}
	}*/
}
