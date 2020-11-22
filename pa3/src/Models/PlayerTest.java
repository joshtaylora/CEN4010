package Models;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {
    //Setting up the variables to be used for most of the tests
    int testInitial = 1500;
    Token testTok = new Token();
    PropertySet[] testSet = {new PropertySet(2), new PropertySet(3), new PropertySet(3), new PropertySet(3), new PropertySet(3), new PropertySet(3), new PropertySet(3), new PropertySet(2), new PropertySet(4), new PropertySet(2)};
    Tile testTile = new Tile("GO", "GO", 0);

    @Test
    void testPurchaseDeed(){
        //Testing the purchaseDeed() method, create a test player and test deed and then do an assert after running
        //purchaseDeed() to see if the deed was input correctly and the account balance are correct.
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
        //Test for performTrade() method, create the test data to use, run performTrade() and then test the ints for
        //the accounts and the deed has been properly traded.
        Player testPlayer = new Player(testInitial, testTok, testTile, testSet);
        Deed testDeed = new Deed(100, 0, 4, 8, 16, 32, 64, 128, 50, 50, 50, "Elisha Avenue", 1);
        testSet[0].addProperty(testDeed);
        Player testTradePlayer = new Player(testInitial, testTok, testTile, testSet);
        Deed[] inputArr = {testDeed};

        testPlayer.performTrade(testTradePlayer, 100, 200, inputArr, inputArr, true);

        assertEquals(testPlayer.getAccBalance(), 1600);

        assertEquals(testTradePlayer.getAccBalance(), 1400);

        assertTrue(testPlayer.getPlayerDeeds()[0].searchProperties(testDeed));



    }

    @Test
    void testNumRailroads() {
        //Test for the numRailroads, this test increment, decrement and get for railroads.
        Player testPlayer = new Player(testInitial, testTok, testTile, testSet);
        testPlayer.increaseRailroads();

        assertTrue(testPlayer.getNumRailroads() == 1);

        testPlayer.decreaseRailroads();

        assertTrue(testPlayer.getNumRailroads() == 0);
    }

    @Test
    void testNumUtilities() {
        //Test for the numUtilities, this test increment, decrement and get for utilities.
        Player testPlayer = new Player(testInitial, testTok, testTile, testSet);
        testPlayer.increaseUtilities();

        assertTrue(testPlayer.getNumUtilities() == 1);

        testPlayer.decreaseUtilities();

        assertTrue(testPlayer.getNumUtilities() == 0);
    }

    @Test
    void testGetSetAccBalance() {
        //Test checks get and set methods for the AccBalance.
        Player testPlayer = new Player(testInitial, testTok, testTile, testSet);
        int testBalance = 1000;

        assertFalse(testPlayer.getAccBalance() == testBalance);

        testPlayer.setAccBalance(testBalance);

        assertTrue(testPlayer.getAccBalance() == testBalance);
    }

    @Test
    void testGetSetJailStatus() {
        //Test checks get and set methods for the JailStatus.
        Player testPlayer = new Player(testInitial, testTok, testTile, testSet);

        assertFalse(testPlayer.getJailStatus());

        testPlayer.setJailStatus(true);

        assertTrue(testPlayer.getJailStatus());
    }

    @Test
    void testGetSetPlayerDeeds() {
        //Test checks get and set methods for the PlayerDeeds array.
        Player testPlayer = new Player(testInitial, testTok, testTile, testSet);
        PropertySet[] testSetter = {new PropertySet(2), new PropertySet(3), new PropertySet(3), new PropertySet(3), new PropertySet(3), new PropertySet(3), new PropertySet(3), new PropertySet(2), new PropertySet(4), new PropertySet(2)};
        PropertySet[] testMod = {new PropertySet(2), new PropertySet(3), new PropertySet(3), new PropertySet(3), new PropertySet(3), new PropertySet(3), new PropertySet(3), new PropertySet(3), new PropertySet(3), new PropertySet(3)};

        assertEquals(testSetter[9].getSetSize(), testPlayer.getPlayerDeeds()[9].getSetSize());
        assertFalse(testMod[9].getSetSize() == testPlayer.getPlayerDeeds()[9].getSetSize());
    }

    @Test
    void testGetSetCurrentTile() {
        //Test checks get and set methods for the CurrentTile variable.
        Player testPlayer = new Player(testInitial, testTok, testTile, testSet);
        Tile testTiler = new Tile("Test", "Test", 9);

        assertFalse(testPlayer.getCurrentTile().getName().compareTo(testTiler.getName()) == 0);

        testPlayer.setCurrentTile(testTiler);

        assertTrue(testPlayer.getCurrentTile().getName().compareTo(testTiler.getName()) == 0);
    }
}
