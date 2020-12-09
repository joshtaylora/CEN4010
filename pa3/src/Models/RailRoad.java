package Models;

public class RailRoad extends Tile {
	// private final int saleValue = 200;
	// private final int propertySet = 8;
	// private final int mortgageValue = 100;

	// ownership variables
	private Player owner = null;
	private boolean mortgaged = false;

	// constructor
	public RailRoad(String name, int position) {

		// parent class variables
		super.name = name;
		super.type = "RailRoad";
		super.position = position;
	}

	// returns the value of a property's mortgage
	public int getMortgageValue() {
		return 100;
	}

	/*
	 * returns the color set of the property legend: 8: RR
	 */
	public int getPropertySet() {
		return 8;
	}

	// returns the sale price of a property
	public int getPrice() {
		return 200;
	}

	// returns owner of space if there is an owner, or returns null if there is no
	// owner
	public Player getOwner() {
		if (owner != null) {
			return owner;
		} else
			return null;
	}

	public boolean getMortagaged(){
		return mortgaged;
	}

	// ****************SETTERS************************************
	// sets owner of property
	public void setOwner(Player possessor) {
		owner = possessor;
	}

	public void setUnMortagaged(){
		mortgaged = false;
	}

	public void setMortagaged(){
		mortgaged = true;
	}
	// ****************METHODS************************************
	// returns the rent amount, depending on the number of houses/hotels present on
	// a property
	public int calcRent() {
		final int baseRent = 25;
		final int rentWithTwo = 50;
		final int rentWithThree = 100;
		final int rentWithFour = 200;

		if(getOwner() == null){
			return 0;
		}
		if(mortgaged){
			return 0;
		}
		else{
			int rrs = getOwner().getNumRailroads();
			switch (rrs) {
				case 1:
					return rentWithTwo;
				case 2:
					return rentWithThree;
				case 3:
					return rentWithFour;
				default:
					return baseRent;

			}
		}

	}

}
