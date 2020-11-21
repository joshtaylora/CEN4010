package Models;

import java.lang.Math;

//This class handles the Dice for the game which 
//includes rolling and checking for doubles
public class Dice {
	// Int Array of size two to store the random dice values
	int dice[] = new int[2];

	// Roll() randomizes the values for index 0 and 1 for the dice array
	public int roll() {
		dice[0] = (int) (1 + Math.random() * 6);
		dice[1] = (int) (1 + Math.random() * 6);
		return dice[0] + dice[1];
	}

	// Checks to see if player rolled doubles
	public boolean checkDoubles() {
		if (dice[0] == dice[1]) {
			return true;
		} else {
			return false;
		}

	}

}