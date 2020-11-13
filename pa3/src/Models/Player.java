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
	public int account;
	private Token playerToken;
	public PropertySet[] playerDeeds;
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
	 * this method is to initiate a trade to another player, trading properties, money or both. Starts off by prompting the player for the object of the 
	 * player they would like to trade with. After that the current players properties and money will pull up and they can choose what they want to trade.
	 * After that the other players properties and money will pop up and the current player then chooses what they want to trade in return. Then all the 
	 * trade information will be sent to the other player using their object and the receiveTrade() method. They the other player either accepts or rejects.
	 * Upon accepting the method will transfer all properties and money. Upon rejection the method will just return.
	 */
	public void initiateTrade() {
		int i;
		int cPlayerMoney;
		int tPlayerMoney;
		Deed cPlayerDeeds[];
		Deed tPlayerDeeds[];
		int cDeedsSize = cPlayerDeeds.length;
		int tDeedsSize = tPlayerDeeds.length;
		Player tradePlayer; //Prompt
		//TODO display a list of players and prompt currentplayer to which player they would like to trade with.
		
		//TODO display and prompt current player for the properties and money to trade
		
		boolean tradeResults = tradePlayer.receiveTrade(cPlayerDeeds, tPlayerDeeds, cPlayerMoney, tPlayerMoney);
		
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
	 * This method is to receive a trade from another player, be it properties, money or both.
	 */
	public boolean receiveTrade(Deed[] deedOffered, Deed[] deedWanted, int moneyOffered, int moneyWanted) {
		/*
		 * TODO somehow display all of this information for the Player to choose if they like the deal or not
		 */
		
		if(true/*Player accepts the deal through a button click*/) {
			return true;
		}
		else if(false/*Player declines the deal through a decline button click*/) {
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
