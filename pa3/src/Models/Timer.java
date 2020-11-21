package Models;

/**
 * @author Alex
 */

import java.util.Calendar;

//Timer class that has two functions to set the game timer and to check if the time limit has been reached.
public class Timer {

	private int minuteMillisecond = 60000;

	private int minutes;
	private int seconds;

	/**
	 * TODO add minute and second initializers
	 */
	// initialize endTimer which will keep track of the time that the game will end
	Calendar endTimer;

	public Timer(int gameTime) {
		long gameTimeMillis = minuteMillisecond * gameTime; // need to mulitiply gameTime by milliseconds in minutes
		// system time when timer activated
		long startTime = System.currentTimeMillis();

	}
	// Updates endTimer to be the current time plus the time limit passed into the
	// function in minutes. No need to return

	// Function to check if the time limit has been reached, returns true if the
	// time limit is reached and false otherwise. SHOULD WE CHECK SECONDS? MAKE IT
	// EXACT TO SECONDS
	public boolean checkTime() {
		Calendar currentTime = Calendar.getInstance();
		if (currentTime.get(Calendar.MINUTE) >= endTimer.get(Calendar.MINUTE)) {
			if (currentTime.get(Calendar.SECOND) >= endTimer.get(Calendar.SECOND)) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	/*
	 * // REMOVED get methods for individual mins, secs, hours long getTime() {
	 * return this.idk; }
	 * 
	 */
}
