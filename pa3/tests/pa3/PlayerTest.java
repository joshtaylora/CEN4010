package pa3;

import Models.PropertySet;
import Models.Token;
import Models.Player;
import Models.Board;
import Models.Tile;
import Models.Deed;
import Resources.ResourceManager;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

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
        Player testPlayer = new Player(initial, testBoard.searchTile("Mediterranean Ave."), propertySetInitializer());
        boolean buyDeed;
        Deed testDeed2 = new Deed(60, 0, 2, 10, 30, 90,160,250,30,50,50,"Mediterranean Ave.", 1);
        testPlayer.purchaseDeed(testDeed2);
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
        Player testPlayer = new Player(0, null, null);
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
        Player testPlayer = new Player(0, null, null);
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
        Player testPlayer = new Player(0, null, null);
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
        Player testPlayer = new Player(0, null, null);
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
        Player testPlayer = new Player(0, null, null);
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
    void decreaseUtilities() { Player testPlayer = new Player(0, null, null);
        boolean testUtil;
        testPlayer.increaseUtilities();
        testPlayer.increaseUtilities();
        testPlayer.increaseUtilities();
        if(testPlayer.getNumUtilities() == 2){
            testUtil = true;
        }
        else{
            testUtil = false;
        }
        assertTrue(testUtil);
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