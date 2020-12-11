package pa3;

import Models.*;
import Resources.ResourceManager;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

//TODO mortgageProperty, rentProperty, payTax, passGo, upgradeProperty, calcNetWorth

class PlayerTest {
    int initial = 1500;
    String sysPath = System.getProperty("user.dir");
    File testToken1Image;
    Token playerToken;
    ResourceManager resourceManager = new ResourceManager();
    String os = resourceManager.os;
    public Board testBoard = new Board();

    @Test
    void testPlayerConstructor() {
        String image1URL = null;
        String image2URL = null;
    }

    @Test
    void testPurchaseDeed() {
        Player testPlayer = new Player("", initial, testBoard.searchTile("Mediterranean Ave."), propertySetInitializer());
        boolean buyDeed;
        Deed testDeed2 = new Deed("Mediterranean Ave.",60, 0, 2, 10, 30, 90,160,250,30,50,50, 1);
        testPlayer.purchaseProperty(testBoard.searchTile("Mediterranean Ave."));
        int testBal = 1440;
        if( testPlayer.getAccBalance() == 1440){
            buyDeed = true;
        }
        else{
            buyDeed = false;
        }
        assertTrue(buyDeed);


    }

    @Test
    void resetDoubles() {
        Player testPlayer = new Player("",0, null, null);
        //Increment to ensure the value is reset
        boolean testDoubles;
        testPlayer.incrementDoubles();
        testPlayer.resetDoubles();
        if(testPlayer.getDoubles() == 0){
            testDoubles = true;
        }
        else{
            testDoubles = false;
        }
        assertTrue(testDoubles);
    }

    @Test
    void incrementDoubles() {
        Player testPlayer = new Player("",0, null, null);
        boolean testInc;
        testPlayer.incrementDoubles();
        if(testPlayer.getDoubles() == 1){
            testInc = true;
        }
        else{
            testInc = false;
        }
        assertTrue(testInc);
    }



    @Test
    void increaseRailroads() {
        Player testPlayer = new Player("",0, null, null);
        boolean testRail;
        testPlayer.increaseRailroads();
        if(testPlayer.getNumRailroads() == 1){
            testRail = true;
        }
        else{
            testRail = false;
        }
        assertTrue(testRail);
    }

    @Test
    void decreaseRailroads() {
        Player testPlayer = new Player("",0, null, null);
        boolean testRail;
        testPlayer.increaseRailroads();
        testPlayer.increaseRailroads();
        testPlayer.increaseRailroads();
        testPlayer.decreaseRailroads();
        if(testPlayer.getNumRailroads() == 2){
            testRail = true;
        }
        else{
            testRail = false;
        }
        assertTrue(testRail);
    }

    @Test
    void increaseUtilities() {
        Player testPlayer = new Player("",0, null, null);
        boolean testUtil;
        testPlayer.increaseUtilities();
        if(testPlayer.getNumUtilities() == 1){
            testUtil = true;
        }
        else{
            testUtil = false;
        }
        assertTrue(testUtil);
    }

    @Test
    void decreaseUtilities() {
        Player testPlayer = new Player("",0, null, null);
        boolean testUtil;
        testPlayer.increaseUtilities();
        testPlayer.increaseUtilities();
        testPlayer.decreaseUtilities();
        if(testPlayer.getNumUtilities() == 1){
            testUtil = true;
        }
        else{
            testUtil = false;
        }
        assertTrue(testUtil);
    }

    //TODO mortgageProperty, rentProperty, payTax, passGo, upgradeProperty, calcNetWorth

    @Test
    void testMortgageProperty(){
        Player testPlayer = new Player("", 0, null, null);
        testPlayer.mortgageProperty(testBoard.searchTile("Baltic Ave."));
        assertEquals(30, testPlayer.getAccBalance());

    }

    @Test
    void testRentProperty(){
        Player testPlayer = new Player("", 100, null, propertySetInitializer());
        Player testPlayer2 = new Player("", 100, null, propertySetInitializer());
        testPlayer2.purchaseProperty(testBoard.searchTile("Baltic Ave."));
        testPlayer.rentProperty(testBoard.searchTile("Baltic Ave."), 2);
        assertEquals(96, testPlayer.getAccBalance());
    }

    @Test
    void testPayTax(){
        Player testPlayer = new Player("", 1000, null, null);
        testPlayer.payTax(testBoard.searchTile("Income Tax"));
        assertEquals(800, testPlayer.getAccBalance());
    }

    @Test
    void testPassGO(){
        Player testPlayer = new Player("", 0, null, null);
        testPlayer.passGO();
        assertEquals(200, testPlayer.getAccBalance());
    }

    @Test
    void testUpgradeProperty(){
        Player testPlayer = new Player("", 1000, null, propertySetInitializer());

        Tile testTile = testBoard.searchTile("Baltic Ave.");
        Tile testTile2 = testBoard.searchTile("Mediterranean Ave.");
        testPlayer.purchaseProperty(testTile);
        testPlayer.purchaseProperty(testTile2);
        Deed testDeed2 = (Deed) testTile ;
        testDeed2.setHouses();
        testPlayer.upgradeProperty(testTile);

        assertEquals(830, testPlayer.getAccBalance());
    }

    @Test
    void testCalcNetWorth(){
        Player testPlayer = new Player("", 1000, null, propertySetInitializer());
        testPlayer.purchaseProperty(testBoard.searchTile("Baltic Ave."));
        testPlayer.purchaseProperty(testBoard.searchTile("Mediterranean Ave."));
        assertEquals(888, testPlayer.calcNetWorth());
    }


    private PropertySet[] propertySetInitializer() {
        // initialize all of the property sets that will go in the array
        PropertySet brown = new PropertySet(2);
        PropertySet lightBlue = new PropertySet(3);
        PropertySet pink = new PropertySet(3);
        PropertySet orange = new PropertySet(3);
        PropertySet red = new PropertySet(3);
        PropertySet yellow = new PropertySet(3);
        PropertySet green = new PropertySet(3);
        PropertySet darkBlue = new PropertySet(2);
        PropertySet railRoad = new PropertySet(4);
        PropertySet utility = new PropertySet(2);

        return new PropertySet[]{ brown, lightBlue, pink, orange, red, yellow, green, darkBlue, railRoad,
                utility };
    }
}