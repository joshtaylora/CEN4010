package Models;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {
    int testInitial = 1500;
    Token testTok = new Token();
    PropertySet[] testSet = {new PropertySet(2), new PropertySet(3), new PropertySet(3), new PropertySet(3), new PropertySet(3), new PropertySet(3), new PropertySet(3), new PropertySet(2), new PropertySet(4), new PropertySet(2)};
    Tile testTile = new Tile("GO", "GO", 0);

    @Test
    void testPurchaseDeed(){
        Player testPlayer = new Player(testInitial, testTok, testTile, testSet);
        Deed testDeed = new Deed(100, 0, 4, 8, 16, 32, 64, 128, 50, 50, 50, "Elisha Avenue", 1);
        int resultMoney = 1400;
        testPlayer.purchaseDeed(testDeed);
        PropertySet[] resultSet = testPlayer.getPlayerDeeds();
        boolean resultDeed = resultSet[0].searchProperties(testDeed);

        assertTrue(resultDeed);

        assertTrue(testPlayer.getAccBalance() == resultMoney);
    }

    @Test
    void testPerformTrade() {

    }

    @Test
    void testNumRailroads() {
        Player testPlayer = new Player(testInitial, testTok, testTile, testSet);
        testPlayer.increaseRailroads();

        assertTrue(testPlayer.getNumRailroads() == 1);

        testPlayer.decreaseRailroads();

        assertTrue(testPlayer.getNumRailroads() == 0);
    }

    @Test
    void testNumUtilities() {
        Player testPlayer = new Player(testInitial, testTok, testTile, testSet);
        testPlayer.increaseUtilities();

        assertTrue(testPlayer.getNumUtilities() == 1);

        testPlayer.decreaseUtilities();

        assertTrue(testPlayer.getNumUtilities() == 0);
    }

    @Test
    void testGetSetAccBalance() {
        Player testPlayer = new Player(testInitial, testTok, testTile, testSet);
        int testBalance = 1000;

        assertFalse(testPlayer.getAccBalance() == testBalance);

        testPlayer.setAccBalance(testBalance);

        assertTrue(testPlayer.getAccBalance() == testBalance);
    }

    @Test
    void testGetSetJailStatus() {
        Player testPlayer = new Player(testInitial, testTok, testTile, testSet);

        assertFalse(testPlayer.getJailStatus());

        testPlayer.setJailStatus(true);

        assertTrue(testPlayer.getJailStatus());
    }

    @Test
    void testGetSetToken() {

    }

    @Test
    void testGetSetProperty() {
        Player testPlayer = new Player(testInitial, testTok, testTile, testSet);
        PropertySet[] testSetter = {new PropertySet(2), new PropertySet(3), new PropertySet(3), new PropertySet(3), new PropertySet(3), new PropertySet(3), new PropertySet(3), new PropertySet(2), new PropertySet(4), new PropertySet(2)};
        PropertySet[] testMod = {new PropertySet(2), new PropertySet(3), new PropertySet(3), new PropertySet(3), new PropertySet(3), new PropertySet(3), new PropertySet(3), new PropertySet(3), new PropertySet(3), new PropertySet(3)};

        assertEquals(testSetter[9].getSetSize(), testPlayer.getPlayerDeeds()[9].getSetSize());
        assertFalse(testMod[9].getSetSize() == testPlayer.getPlayerDeeds()[9].getSetSize());
    }

    @Test
    void testGetSetCurrentTile() {
        Player testPlayer = new Player(testInitial, testTok, testTile, testSet);
        Tile testTiler = new Tile("Test", "Test", 9);

        assertFalse(testPlayer.getCurrentTile().getName().compareTo(testTiler.getName()) == 0);

        testPlayer.setCurrentTile(testTiler);

        assertTrue(testPlayer.getCurrentTile().getName().compareTo(testTiler.getName()) == 0);
    }
}
