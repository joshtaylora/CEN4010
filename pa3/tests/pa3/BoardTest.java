package tests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class BoardTest {

    @Test
    void testMove(){
        Board game =  new Board();

        Tile input = new Deed(400, 7, 50, 200, 600, 1400, 1700, 2000, 200, 200, 200, "Boardwalk", 39);
        Tile output = new RailRoad("Reading Railroad", 5);

        Tile test = game.move(input, 6);

        assertEquals(test, output);
    }

    @Test
    void testSearchTile(){
        Board game =  new Board();
        Tile output = tiles[20] = new Tile("Free Parking", "none", 20);

        Tile test = game.searchTile("free parking");

        assertEquals(test, output);
    }
}
