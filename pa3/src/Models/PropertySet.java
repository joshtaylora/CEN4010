package Models;

public class PropertySet {

	// setting variables needed for this class
	private int setSize;
	private int currentNumProperties;
	private Deed[] propertiesInSet;

	/**
	 * Constructor for the PropertySet class it will take in an integer called
	 * pInSet as the size of the set. will initialize the setSize and
	 * propertiesInSet variables using the value.
	 * 
	 * @param pInSet
	 */
	public PropertySet(int pInSet) {
		setSize = pInSet;
		this.propertiesInSet = new Deed[pInSet];
		this.currentNumProperties = 0;
	}

	/**
	 * This method takes in a Deed type variable and adds it to the propertiesInSet
	 * array
	 * 
	 * @param property
	 */
	public void addProperty(Deed property) {
		propertiesInSet[currentNumProperties] = property;
		currentNumProperties++;

	}

	/**
	 * Searches through the property array and checks if the deed given as param is
	 * in the array
	 * 
	 * @param key
	 * @return boolean
	 */
	public boolean searchProperties(Deed key) {
		int i;

		for (i = 0; i < currentNumProperties; i++) {
			if (propertiesInSet[i].getName().equals(key.getName())) {
				return true;
			}
		}

		return false;
	}

	/**
	 * This method is to remove a property from a player, mainly used with the trade
	 * class.
	 * 
	 * @param property
	 */
	public void removeProperty(Deed property) {
		int i;
		int j;

		for (i = 0; i < currentNumProperties; i++) {
			if (propertiesInSet[i].getName().equals(property.getName())) {
				j = i;
				if (j == currentNumProperties - 1) {
					currentNumProperties--;
					return;
				} else {
					for (j = i; j < currentNumProperties - 1; j++) {
						propertiesInSet[j] = propertiesInSet[j + 1];
					}
					break;
				}
			}
		}
		currentNumProperties--;

	}

	/**
	 * This method is a little bit kinky, it shouldn't need to take in a player type
	 * as it should be going through a specific player to get here and be on a
	 * specific color set.
	 * 
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

	/**
	 * returns the setSize value
	 * 
	 * @return int
	 */
	public int getSetSize() {

		return setSize;
	}

	/**
	 * Sets the setSize value based off size param
	 * 
	 * @param size
	 */
	public void setSetSize(int size) {
		setSize = size;

		return;
	}

	/**
	 * returns the current number of properties in this set
	 * 
	 * @return int
	 */
	public int getCurrentNumProperties() {

		return currentNumProperties;
	}

	/**
	 * sets the current number of properties in this set
	 * 
	 * @param number
	 */
	public void setCurrentNumProperties(int number) {
		currentNumProperties = number;

		return;
	}

	/**
	 * returns the entire property set
	 * 
	 * @return Deed[]
	 */
	public Deed[] getPropertiesInSet() {

		return propertiesInSet;
	}

	/**
	 * sets the entire property set
	 * 
	 * @param properties
	 */
	public void setPropertiesInSet(Deed[] properties) {
		propertiesInSet = properties;

		return;
	}
}
