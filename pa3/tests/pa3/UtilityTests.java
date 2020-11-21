package tests;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;
public class UtilityTests {

    @Test
    void testGetPropertySet (){
        Utility test = new Utility("Electric Company" ,12);
        int correct = 9;

        assertEquals(correct, test.getPropertySet());
    }

    @Test
    void testGetPrice (){
        Utility test = new Utility("Electric Company" ,12);
        int correct = 150;

        assertEquals(correct, test.getPrice());
    }

    @Test
    void testGetOwner (){
        Utility test = new Utility("Electric Company" ,12);
        //make sure Player begins as null
        assertNull(test.getOwner());
    }

    @Test
    void testSetOwner (){
        Utility test = new Utility("Electric Company" ,12);
        //initialize a random player object
        Token tok = new Token();
        PropertySet set = new PropertySet(3);
        //TODO: make sure this player constructor is up to date
        Player dummy = new Player(1500, tok, test, set);

        //set ownership to player
        test.setOwner(dummy);

        assertEquals(test.getOwner(), dummy);
    }

    @Test
    void testCalcRent (){
        Utility test = new Utility("Electric Company" ,12);
        int onerent = 4;
        int tworent = 10;

        //make sure rent is correct for test rolls
        for(int i=1; i<13; i++){
            assertEquals(onerent*i, test.calcRent(i, 1));
        }

        for(int i=1; i<13; i++){
            assertEquals(tworent*i, test.calcRent(i, 2));
        }
    }
}