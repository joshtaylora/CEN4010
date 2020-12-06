package Models;

import Resources.OSValidator;
import javafx.scene.image.Image;

import java.io.File;
import java.lang.Math;
import java.net.MalformedURLException;

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
		File die1File = new File(osV.getPathToFile("die1.png", "Resources"));
		File die2File = new File(osV.getPathToFile("die2.png", "Resources"));
		File die3File = new File(osV.getPathToFile("die3.png", "Resources"));
		File die4File = new File(osV.getPathToFile("die4.png", "Resources"));
		File die5File = new File(osV.getPathToFile("die5.png", "Resources"));
		File die6File = new File(osV.getPathToFile("die6.png", "Resources"));
		Image[] testDieArray = null;
		try {
			testDieArray = new Image[6];
			testDieArray[0] = new Image(die1File.toURI().toURL().toString());
			testDieArray[1] = new Image(die2File.toURI().toURL().toString());
			testDieArray[2] = new Image(die3File.toURI().toURL().toString());
			testDieArray[3] = new Image(die4File.toURI().toURL().toString());
			testDieArray[4] = new Image(die5File.toURI().toURL().toString());
			testDieArray[5] = new Image(die6File.toURI().toURL().toString());
		} catch (MalformedURLException urlE) {
			System.out.println("ERROR: dice image could not be found. System terminating");
			urlE.printStackTrace();
			System.exit(1);
		}
		if (testDieArray == null) {
			System.out.println("ERROR: dice images could not be found. System terminating");
			System.exit(1);
		}
		else {
			this.diceImages = new Image[6];
			this.diceImages = testDieArray;
		}
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
	public Image[] getDiceRollImages() {
		Image[] returnImages = new Image[2];
		returnImages[0] = this.diceImages[getDiceOneResult() - 1];
		returnImages[1] = this.diceImages[getDiceTwoResult() - 1];
		return returnImages;
	}
}