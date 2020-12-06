package Models;

public class Board {
	private Tile tiles[] = new Tile[40];

	// constructor
	public Board() {
		// create all tiles here
		tiles[0] = new Tile("GO", "GO", 0);
		tiles[1] = new Deed(60, 0, 2, 10, 30, 90, 160, 250, 30, 50, 50, "Mediterranean Ave.", 1);
		tiles[2] = new Tile("Community Chest", "none", 2);
		tiles[3] = new Deed(60, 0, 4, 20, 60, 180, 320, 450, 30, 50, 50, "Baltic Ave.", 3);
		tiles[4] = new IncomeTax("Income Tax", "IncomeTax", 4);
		tiles[5] = new RailRoad("Reading Railroad", 5);
		tiles[6] = new Deed(100, 1, 6, 30, 90, 270, 400, 550, 50, 50, 50, "Oriental Ave.", 6);
		tiles[7] = new Tile("Chance", "none", 7);
		tiles[8] = new Deed(100, 1, 6, 30, 90, 270, 400, 550, 50, 50, 50, "Vermont Ave.", 8);
		tiles[9] = new Deed(120, 1, 8, 40, 100, 300, 450, 600, 60, 50, 50, "Connecticut Ave.", 9);
		tiles[10] = new Tile("Jail", "Jail", 10);
		tiles[11] = new Deed(140, 2, 10, 50, 150, 450, 625, 750, 70, 100, 100, "St. Charles Place", 11);
		tiles[12] = new Utility("Electric Company", 12);
		tiles[13] = new Deed(140, 2, 10, 50, 150, 450, 625, 750, 70, 100, 100, "States Ave.", 13);
		tiles[14] = new Deed(160, 2, 12, 60, 180, 500, 700, 900, 80, 100, 100, "Virginia Ave.", 14);
		tiles[15] = new RailRoad("Pennsylvania Railroad", 15);
		tiles[16] = new Deed(180, 3, 14, 70, 200, 550, 750, 950, 90, 100, 100, "St. James Place", 16);
		tiles[17] = new Tile("Community Chest", "none", 17);
		tiles[18] = new Deed(180, 3, 14, 70, 200, 550, 750, 950, 90, 100, 100, "Tennessee Avenue", 18);
		tiles[19] = new Deed(200, 3, 16, 80, 220, 600, 800, 1000, 100, 100, 100, "New York Avenue", 19);
		tiles[20] = new Tile("Free Parking", "none", 20);
		tiles[21] = new Deed(220, 4, 18, 90, 250, 700, 875, 1050, 110, 150, 150, "Kentucky Avenue", 21);
		tiles[22] = new Tile("Chance", "none", 22);
		tiles[23] = new Deed(220, 4, 18, 90, 250, 700, 875, 1050, 110, 150, 150, "Indiana Ave.", 23);
		tiles[24] = new Deed(240, 4, 20, 100, 300, 750, 925, 1100, 120, 150, 150, "Illinois Ave.", 24);
		tiles[25] = new RailRoad("B&O Railroad", 25);
		tiles[26] = new Deed(260, 5, 22, 110, 330, 800, 975, 1150, 130, 150, 150, "Atlantic Ave.", 26);
		tiles[27] = new Deed(260, 5, 22, 110, 330, 800, 975, 1150, 130, 150, 150, "Ventnor Ave.", 27);
		tiles[28] = new Utility("Water Works", 28);
		tiles[29] = new Deed(280, 5, 24, 120, 360, 850, 1025, 1200, 140, 150, 150, "Marvin Gardens", 29);
		tiles[30] = new Tile("Go To Jail", "GoToJail", 30);
		tiles[31] = new Deed(300, 6, 26, 130, 390, 900, 1100, 1275, 150, 200, 200, "Pacific Ave.", 31);
		tiles[32] = new Deed(300, 6, 26, 130, 390, 900, 1100, 1275, 150, 200, 200, "North Carolina Ave.", 32);
		tiles[33] = new Tile("Community Chest", "none", 33);
		tiles[34] = new Deed(320, 6, 28, 150, 450, 100, 1200, 1400, 160, 200, 200, "Pennsylvania Ave.", 34);
		tiles[35] = new RailRoad("Short Line", 35);
		tiles[36] = new Tile("Chance", "none", 36);
		tiles[37] = new Deed(350, 7, 35, 175, 500, 1100, 1300, 1500, 175, 200, 200, "Park Place", 37);
		tiles[38] = new LuxuryTax("Luxury Tax", "LuxuryTax", 38);
		tiles[39] = new Deed(400, 7, 50, 200, 600, 1400, 1700, 2000, 200, 200, 200, "Boardwalk", 39);
	}

	// given the current Tile and the spaces to go, returns the tile the token has
	// landed on
	public Tile move(Tile current, int spaces) {
		int here = current.getPosition();
		here = (here + spaces) % 40;
		return tiles[here];
	}

	// given a tile's name(case insensitive), return the Tile object. If Tile is not
	// found, return null
	public Tile searchTile(String name) {
		for (int i = 0; i < 40; i++) {
			if (tiles[i].getName().equalsIgnoreCase(name)) {
				return tiles[i];
			}
		}
		return null;
	}
}
