package pa3;

import Models.Player;
import Models.PropertySet;
import Models.RailRoad;
import Models.Token;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class RailroadTests {

    @Test
    void testGetMortgageValue (){
        RailRoad test = new RailRoad("Reading Railroad", 5);
        int correct = 100;

        assertEquals(correct, test.getMortgageValue());
    }

    @Test
    void testGetPropertySet (){
        RailRoad test = new RailRoad("Reading Railroad", 5);
        int correct = 8;

        assertEquals(correct, test.getPropertySet());
    }

    @Test
    void testGetPrice (){
        RailRoad test = new RailRoad("Reading Railroad", 5);
        int correct = 200;

        assertEquals(correct, test.getPrice());
    }

    @Test
    void testGetOwner (){
        RailRoad test = new RailRoad("Reading Railroad", 5);
        //make sure Player begins as null
        assertNull(test.getOwner());
    }

    @Test
    void testSetOwner (){
        RailRoad test = new RailRoad("Reading Railroad", 5);
        //initialize a random player object
        PropertySet[] arry = new PropertySet[1];
        Player dummy = new Player(1500, test, arry);

        //set ownership to player
        test.setOwner(dummy);

        assertEquals(test.getOwner(), dummy);
    }

    @Test
    void testCalcRent (){
        RailRoad test = new RailRoad("Reading Railroad", 5);
        int rent = 25;

        //make sure rent is correct for each level of railroad ownership
        for(int i=0; i<4; i++){
            assertEquals(rent, test.calcRent(i));
            rent *= 2;
        }
    }
}
