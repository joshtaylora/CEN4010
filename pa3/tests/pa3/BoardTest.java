package pa3;

import Models.Board;
import Models.Deed;
import Models.RailRoad;
import Models.Tile;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class BoardTest {

    @Test
    void testMove(){
        Board game =  new Board();

        Tile input = new Deed(400, 7, 50, 200, 600, 1400, 1700, 2000, 200, 200, 200, "Boardwalk", 39);
        Tile output = new RailRoad("Reading Railroad", 5);

        Tile test = game.move(input, 6);

        assertEquals(output.getName(), test.getName());
    }

    @Test
    void testSearchTile(){
        Board game =  new Board();
        Tile output = new Tile("Free Parking", "none", 20);

        Tile test = game.searchTile("free parking");

        assertEquals(output.getName(), test.getName());
    }
}
