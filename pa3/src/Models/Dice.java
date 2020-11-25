package Models;

import Resources.OSValidator;
import javafx.scene.image.Image;

import java.lang.Math;

//This class handles the Dice for the game which 
//includes rolling and checking for doubles
public class Dice {
	Image[] diceImages;
	private int[] dice;
	private boolean doubleRoll;

	// Constructor that initializes the dice
	public Dice() {
		// initialize the array that stores the results of the dice roll
		this.dice = new int[2];
		this.doubleRoll = false;
		diceImages = new Image[6];
		OSValidator osV = new OSValidator();
		diceImages[0] = new Image(osV.getPathToFile("die1.png", "Resources"));
		diceImages[1] = new Image(osV.getPathToFile("die2.png", "Resources"));
		diceImages[2] = new Image(osV.getPathToFile("die3.png", "Resources"));
		diceImages[3] = new Image(osV.getPathToFile("die4.png", "Resources"));
		diceImages[4] = new Image(osV.getPathToFile("die5.png", "Resources"));
		diceImages[5] = new Image(osV.getPathToFile("die6.png", "Resources"));

	}

	// Roll() randomizes the values for index 0 and 1 for the dice array
	public void roll() {
		this.dice[0] = (int) (1 + Math.random() * 6);
		this.dice[1] = (int) (1 + Math.random() * 6);
		setDoubleResults();
	}
	// used to update the number of spaces that the player's Token will advance on the board
	public int getDiceRollValue() {
		return this.dice[0] + this.dice[1];
	}

	/**
	 * Checks to see if both dice rolls were identical => ie. the player rolled doubles and sets the condition variable
	 */
	private void setDoubleResults() {
		this.doubleRoll =  (dice[0] == dice[1]);
	}
	public boolean getDoubleResults() {
		return this.doubleRoll;
	}

	public int getDiceOneResult() {
		return this.dice[0];
	}

	public int getDiceTwoResult() {
		return this.dice[1];
	}

}