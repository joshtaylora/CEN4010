package pa3;

import static org.junit.jupiter.api.Assertions.*;

import Models.Game;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class GameTest {

	@Test
	void testConstructor() {
		ArrayList<Integer> playerTurnList = new ArrayList<>();
		playerTurnList.add(1);
		playerTurnList.add(2);
		playerTurnList.add(3);
		playerTurnList.add(4);

		// make a new Game for 2 players with a time limit of 1 minutes
		Game testGame1 = new Game(2, 1, playerTurnList);
	}

}