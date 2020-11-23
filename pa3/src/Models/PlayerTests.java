package Models;

import org.junit.Test;
import static org.junit.Assert.*;

class PlayerTests {

    int initial = 1500;
    Token playerToken = new Token();
    Tile currentTile = new Tile("GO", "GO", 0);
    PropertySet[] playerDeeds = new PropertySet[10];
    playerDeeds[0] = new PropertySet("2");
    playerDeeds[1] = new PropertySet("3");
    playerDeeds[2] = new PropertySet("3");
    playerDeeds[3] = new PropertySet("3");
    playerDeeds[4] = new PropertySet("3");
    playerDeeds[5] = new PropertySet("3");
    playerDeeds[6] = new PropertySet("3");
    playerDeeds[7] = new PropertySet("3");
    playerDeeds[8] = new PropertySet("4");
    playerDeeds[9] = new PropertySet("2");

    @Test
    void testPurchaseDeed() {
        Player testPlayer = new Player(initial, playerToken, currentTile, playerDeeds);
        Deed testDeed =  new Deed(100, 1, 6, 30, 90, 270, 400, 550, 50, 50, 50, "Oriental Avenue", 6);
        int testint = 1400;

        testPlayer.purchaseDeed(testDeed);

        assertEquals(testint, testPlayer.getAccBalance());
    }
}