package pa3;

import static org.junit.jupiter.api.Assertions.*;

import Models.Game;
import Models.Player;
import Models.Board;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class GameTest {
	Board testBoard = new Board();

	@Test
	void testConstructor() {

		// make a new Game for 2 players with a time limit of 1 minutes
		Game testGame1 = new Game(2, 1);
	}

	@Test
	void testPlayerRoll(){
		Game testGame = new Game(1, 0);
		Player testPlayer = new Player(0, testBoard.searchTile("Go"), null);
		testGame.playerRoll();
		assertTrue(testPlayer.getRollStatus());
	}

	@Test
	void testAdvancePlayerTile(){
		Player testPlayer = new Player(0, testBoard.searchTile("Go"), null);
		Game testGame = new Game(1, 0);
		testGame.advancePlayerTile(1);
		assertEquals("Mediterranean Ave.", testPlayer.getCurrentTile().getName());
	}


	@Test
	void testTokenImageArrayInitializer(){
		//make sure array indexes are not equal to null
	}

	@Test
	void testPropertySetInitializer(){
		//make sure array indexes are not equal to null
	}


}