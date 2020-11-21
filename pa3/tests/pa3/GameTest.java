package pa3;

import static org.junit.jupiter.api.Assertions.*;

import Models.Game;
import org.junit.jupiter.api.Test;

class GameTest {

	@Test
	void testConstructor() {

		// make a new Game for 2 players with a time limit of 1 minutes
		Game testGame1 = new Game(2, 1);
	}

}