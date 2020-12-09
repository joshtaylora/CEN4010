package pa3;

import Models.Deed;
import Models.Player;
import Models.PropertySet;
import Models.Token;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DeedTests {

    @Test
    void testGetMortgageValue (){
        Deed test = new Deed(60,
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
                "Mediterranean Ave.",
                1);
        int correct = 30;

        assertEquals(correct, test.getMortgageValue());
    }

    @Test
    void testGetPropertySet (){
        Deed test = new Deed(60,
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
                "Mediterranean Ave.",
                1);
        int correct = 0;

        assertEquals(correct, test.getPropertySet());
    }

    @Test
    void testGetPrice (){
        Deed test = new Deed(60,
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
                "Mediterranean Ave.",
                1);
        int correct = 60;

        assertEquals(correct, test.getPrice());
    }

    @Test
    void testGetHouseCost (){
        Deed test = new Deed(60,
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
                "Mediterranean Ave.",
                1);
        int correct = 50;

        assertEquals(correct, test.getHouseCost());
    }

    @Test
    void testGetHotelCost (){
        Deed test = new Deed(60,
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
                "Mediterranean Ave.",
                1);
        int correct = 50;

        assertEquals(correct, test.getHotelCost());
    }

    @Test
    void testGetOwner (){
        Deed test = new Deed(60,
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
                "Mediterranean Ave.",
                1);
        //make sure Player begins as null
        assertNull(test.getOwner());
    }

    @Test
    void testSetOwner (){
        Deed test = new Deed(60,
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
                "Mediterranean Ave.",
                1);
        //initialize a random player object
        PropertySet[] arry = new PropertySet[1];
        Player dummy = new Player(1500, test, arry, "player");

        //set ownership to player
        test.setOwner(dummy);

        assertEquals(test.getOwner(), dummy);
    }

    @Test
    void testSetHouses (){
        Deed test = new Deed(60,
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
                "Mediterranean Ave.",
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
        Deed test = new Deed(60,
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
                "Mediterranean Ave.",
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

}
