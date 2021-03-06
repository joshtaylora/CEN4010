package Models;

/**
 * @author Elisha
 * @version 11/5/20
 */
public class Player{

	// the variables that are part of the Player class
	private int account;
	private Token playerToken;
	public PropertySet[] playerPropertySetArray;
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
	 * @param playerPropertySetArray an array of property sets that are initially empty but initialized to hold the correct number
	 *                    of deeds
	 */
	public Player( String name, int initial, Tile currentTile, PropertySet[] playerPropertySetArray) {
		this.account = initial;
		this.playerPropertySetArray = playerPropertySetArray;
		this.currentTile = currentTile;
		this.playerName = name;
		this.inJail = false;
		this.numRailroads = 0;
		this.numUtilities = 0;
		this.doubles = 0;
		this.rollStatus = false;
		this.diceRollResults = 0;
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
				playerPropertySetArray[obj.getPropertySet()].addProperty(obj);
				obj.setOwner(this);
				//checks if player has monopoly and if the monopoly was already checked
				if((this.getPlayerPropertySetArray()[obj.getPropertySet()].checkMonopoly()) && (obj.getHouses() < 1)){
					obj.setHouses();
				}
				this.account -= obj.getPrice();
				break;
			case "RailRoad":
				RailRoad rr = (RailRoad) tile;
				rr.setOwner(this);
				increaseRailroads();
				this.account -= rr.getPrice();
				break;
			case "Utility":
				Utility util = (Utility) tile;
				util.setOwner(this);
				increaseUtilities();
				this.account -= util.getPrice();
				break;
			default:
				break;
		}
	}

	/**
	 * Takes in a tile, determines its property type, and mortgages or unmortgages
	 *
	 * @param tile the tile being mortgaged
	 */
	public void mortgageProperty(Tile tile){
		String type = tile.getType();
		switch (type) {
			case "Deed":
				Deed obj = (Deed) tile;
				if(obj.getMortgaged()){
					this.account -= obj.getMortgageValue();
				}
				else{
					this.account += obj.getMortgageValue();
				}
				obj.setMortgaged();
				break;
			case "RailRoad":
				RailRoad obj2 = (RailRoad) tile;
				if(obj2.getMortagaged()){
					this.account -= obj2.getMortgageValue();
				}
				else{
					this.account += obj2.getMortgageValue();
				}
				obj2.setMortagaged();
				break;
			default:
				break;
		}
	}

	/**
	 * Takes in a tile, determines its property type, and transferrs rent between players
	 *
	 * @param tile the tile that rent is being paid on
	 */
	public void rentProperty(Tile tile, int roll){
		String type = tile.getType();
		switch (type) {
			case "Deed":
				Deed obj = (Deed) tile;
				this.account -= obj.calcRent();
				obj.getOwner().setAccBalance(obj.getOwner().getAccBalance() + obj.calcRent());
				break;
			case "RailRoad":
				RailRoad obj2 = (RailRoad) tile;
				this.account -= obj2.calcRent();
				obj2.getOwner().setAccBalance(obj2.getOwner().getAccBalance() + obj2.calcRent());
				break;
			case "Utility":
				Utility obj3 = (Utility) tile;
				this.account -= obj3.calcRent(roll);
				obj3.getOwner().setAccBalance(obj3.getOwner().getAccBalance() + obj3.calcRent(roll));
				break;
			default:
				break;
		}
	}

	/**
	 * Takes in a tile, determines its tax type, and subtracts tax from player account
	 *
	 * @param tile the tile being taxed
	 */
	public void payTax(Tile tile){
		String type = tile.getType();
		switch (type) {
			case "LuxuryTax":
				account -= 75;
				break;
			case "IncomeTax":
				account -= 200;
				break;
			default:
				break;
		}
	}

	/**
	 * Adds 200 dollars to player account
	 *
	 */
	public void passGO(){
		account += 200;
	}

	/**
	 * Takes in a tile, casts it to a Deed, and upgrades it
	 *
	 * @param tile the tile being upgraded
	 */
	public void upgradeProperty(Tile tile){
		Deed obj = (Deed) tile;
		account -= obj.getUpgradeCost();
		obj.setHouses();
	}

	/**
	 * liquidates players assets
	 * @return returns the combined rental value of all owned properties plus the account value of the player
	 */
	public Integer calcNetWorth(){
		Integer ret;
		int sum = account;
		for (PropertySet ps: playerPropertySetArray){
			for (Deed deed: ps.getPropertiesInSet()) {
				if(deed != null){
					sum += deed.calcRent();
				}
			}
		}
		account = sum;
		ret = Integer.valueOf(sum);
		return ret;
	}

	/*
	* REMOVED tradePlayer METHOD
	* method removed because Player Objects do not directly interact with each other
	*/

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

	public PropertySet[] getPropertySetArray() {
		return this.playerPropertySetArray;
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
	public PropertySet[] getPlayerPropertySetArray() {
		return playerPropertySetArray;
	}
	
	/**
	 * sets the player's PropertySet using the deeds param
	 * @param deeds
	 */
	public void setPlayerPropertySetArray(PropertySet[] deeds) {
		playerPropertySetArray = deeds;
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
