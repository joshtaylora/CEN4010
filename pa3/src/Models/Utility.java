package Models;

public class Utility extends Tile {
	private final int saleValue = 150;
	private final int propertySet = 9;
	private final int baseRentMult = 4;
	private final int rentWithTwoMult = 10;

	// ownership variables
	private Player owner = null;

	// constructor
	public Utility(String name, int position) {

		// parent class variables
		super.name = name;
		super.type = "Utility";
		super.position = position;
	}

	/*
	 * returns the color set of the property legend: 9: RR
	 */
	public int getPropertySet() {
		return propertySet;
	}

	// returns the sale price of a property
	public int getPrice() {
		return saleValue;
	}

	// returns owner of space if there is an owner, or returns null if there is no
	// owner
	public Player getOwner() {
		if (owner != null) {
			return owner;
		} else
			return null;
	}

	// ****************SETTERS************************************
	// sets owner of property
	public void setOwner(Player possessor) {
		owner = possessor;
	}

	// ****************METHODS************************************
	// returns the rent amount, depending on the number of utilities owned and dice
	// roll
	public int calcRent(int roll, int us) {
		switch (us) {
		case 1:
			return baseRentMult * roll;
		case 2:
			return rentWithTwoMult * roll;
		default:
			return 0;

		}
	}

}
