package pa3;

import Models.Token;
import Resources.ResourceManager;
import org.junit.jupiter.api.Test;

import java.io.File;

class PlayerTest {
    int initial = 1500;
    String sysPath = System.getProperty("user.dir");
    File testToken1Image;
    Token playerToken;
    ResourceManager resourceManager = new ResourceManager();
    String os = resourceManager.os;

    @Test
    void testPlayerConstructor() {
        String image1URL = null;
        String image2URL = null;
    }

    @Test
    void testPurchaseDeed() {
        //Player testPlayer = new Player()
    }

    @Test
    void performTrade() {
    }

    @Test
    void getDoubles() {
    }

    @Test
    void resetDoubles() {
    }

    @Test
    void incrementDoubles() {
    }

    @Test
    void getNumRailroads() {
    }

    @Test
    void getNumUtilities() {
    }

    @Test
    void increaseRailroads() {
    }

    @Test
    void decreaseRailroads() {
    }

    @Test
    void increaseUtilities() {
    }

    @Test
    void decreaseUtilities() {
    }

    @Test
    void getAccBalance() {
    }

    @Test
    void setAccBalance() {
    }

    @Test
    void getJailStatus() {
    }

    @Test
    void setJailStatus() {
    }

    @Test
    void getPlayerToken() {
    }

    @Test
    void setPlayerToken() {
    }

    @Test
    void getPlayerDeeds() {
    }

    @Test
    void setPlayerDeeds() {
    }

    @Test
    void getCurrentTile() {
    }

    @Test
    void setCurrentTile() {
    }

    @Test
    void setRollStatus() {
    }

    @Test
    void getRollStatus() {
    }
}