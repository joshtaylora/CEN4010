package pa3;

import Models.Deed;
import Models.Player;
import Models.PropertySet;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DeedTests {

    static Deed test = new Deed("Mediterranean Ave.",
            60,
            0,
            2,
            10,
            30,
            90,
            160,
            250,
            30,
            50,
            50,
            1);

    @Test
    void testGetMortgageValue (){
        int correct = 30;

        assertEquals(correct, test.getMortgageValue());
    }

    @Test
    void testGetPropertySet (){
        Deed test = new Deed("Mediterranean Ave.",
                60,
                0,
                2,
                10,
                30,
                90,
                160,
                250,
                30,
                50,
                50,
                1);
        int correct = 0;

        assertEquals(correct, test.getPropertySet());
    }

    @Test
    void testGetPrice (){
        Deed test = new Deed("Mediterranean Ave.",
                60,
                0,
                2,
                10,
                30,
                90,
                160,
                250,
                30,
                50,
                50,
                1);
        int correct = 60;

        assertEquals(correct, test.getPrice());
    }

    @Test
    void testGetHouseCost (){
        Player testPlayer = new Player("", 200, null, propertySetInitializer());
        Deed test = new Deed("Mediterranean Ave.",
                60,
                0,
                2,
                10,
                30,
                90,
                160,
                250,
                30,
                50,
                50,
                1);
        testPlayer.purchaseProperty(test);
        test.setHouses();
        int correct = 50;

         assertEquals(correct, test.getUpgradeCost());
    }

    @Test
    void testGetHotelCost (){
        Player testPlayer = new Player("", 200, null, propertySetInitializer());
        Deed test = new Deed("Mediterranean Ave.",
                60,
                0,
                2,
                10,
                30,
                90,
                160,
                250,
                30,
                50,
                50,
                1);
        testPlayer.purchaseProperty(test);
        test.setHouses();
        int correct = 50;

         assertEquals(correct, test.getUpgradeCost());
    }

    @Test
    void testGetOwner (){
        Deed test = new Deed("Mediterranean Ave.",
                60,
                0,
                2,
                10,
                30,
                90,
                160,
                250,
                30,
                50,
                50,
                1);
        //make sure Player begins as null
        assertNull(test.getOwner());
    }

    @Test
    void testSetOwner (){
        Deed test = new Deed( "Mediterranean Ave.",
                60,
                0,
                2,
                10,
                30,
                90,
                160,
                250,
                30,
                50,
                50,
                1);
        //initialize a random player object
        PropertySet[] arry = new PropertySet[1];
        Player dummy = new Player("player", 1500, test, arry);

        //set ownership to player
        test.setOwner(dummy);

        assertEquals(test.getOwner(), dummy);
    }

    @Test
    void testSetHouses (){
        Deed test = new Deed("Mediterranean Ave.",
                60,
                0,
                2,
                10,
                30,
                90,
                160,
                250,
                30,
                50,
                50,
                1);
        int init = 2;
        int after = 4;
        int last = 250;

        //confirm base rent as original rent value
        assertEquals(test.calcRent(), init);

        //indicate that color set is acquired (1)
        test.setHouses();
        assertEquals(test.calcRent(), after);

        //show that method will not exceed value of six
        test.setHouses();
        test.setHouses();
        test.setHouses();
        test.setHouses();
        test.setHouses();
        test.setHouses();

        assertEquals(test.calcRent(), last);
    }

    @Test
    void testCalcRent (){
        Deed test = new Deed("Mediterranean Ave.",
                60,
                0,
                2,
                10,
                30,
                90,
                160,
                250,
                30,
                50,
                50,
                1);
        //confirm all rent values correct
        int init = 2;
        int cs = 4;
        int one = 10;
        int two = 30;
        int thre = 90;
        int four = 160;
        int hotel = 250;

        //confirm base rent as original rent value
        assertEquals(test.calcRent(), init);

        //indicate that color set is acquired
        test.setHouses();
        assertEquals(test.calcRent(), cs);

        //indicate that one house owned
        test.setHouses();
        assertEquals(test.calcRent(), one);

        //indicate that two house owned
        test.setHouses();
        assertEquals(test.calcRent(), two);

        //indicate that three house owned
        test.setHouses();
        assertEquals(test.calcRent(), thre);

        //indicate that four house owned
        test.setHouses();
        assertEquals(test.calcRent(), four);

        //indicate that hotel owned
        test.setHouses();
        assertEquals(test.calcRent(), hotel);
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
