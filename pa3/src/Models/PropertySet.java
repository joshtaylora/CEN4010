package Models;

public class PropertySet {

	//setting variables needed for this class
	private int setSize;
	private int currentNumProperties;
	private Deed[] propertiesInSet;
	
	/**
	 * Constructor for the PropertySet class it will take in an integer called pInSet as the size of the set.
	 * will initialize the setSize and propertiesInSet variables using the value.
	 */
	public PropertySet(int pInSet) {
		setSize = pInSet;
		this.propertiesInSet = new Deed[pInSet];
		this.currentNumProperties = 0;
	}
	
	/**
	 * This method takes in a Deed type variable and adds it to the propertiesInSet array 
	 */
	public void addProperty(Deed property) {
		propertiesInSet[currentNumProperties] = property;
		currentNumProperties++;

	}
	
	/**
	 * This method is to remove a property from a player, mainly used with the trade class.
	 */
	public void removeProperty(Deed property) {
		//foreach property check if it is the correct property if so remove
		
		currentNumProperties--;

	}
	
	
	/**
	 * This method is a little bit kinky, it shouldn't need to take in a player type as it should be going
	 * through a specific player to get here and be on a specific color set.
	 * @return true if they have a monopoly and false if they don't.
	 */
	public boolean checkMonopoly() {
		if (currentNumProperties == setSize) {
			return true;
		}
		
		else {
			return false;
		}
	}
}
