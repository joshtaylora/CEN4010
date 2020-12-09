package Models;

public class Tile {
	String name;
	String type;
	int position;

	// default constructor
	public Tile() {
	}

	// constructor
	public Tile(String name, String type, int position) {
		this.name = name;
		this.type = type;
		this.position = position;
	}

	// returns the name of the tile
	public String getName() {
		return name;
	}

    //returns the subclass of the tile
    /*
    Types are as follows:
    -Deed
    -LuxuryTax
    -IncomeTax
    -Go
    -Jail
    -GoToJail
    -RailRoad
    -Utility
    -none (nothing to do on this tile)
     */
    public String getType(){
        return type;
    }

	// returns the index of a tile in Board's tile array
	public int getPosition() {
		return position;
	}

}
