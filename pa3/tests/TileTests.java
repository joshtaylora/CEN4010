import Models.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
public class TileTests {

    //test all getters on children of Tile, as well as Tile itself

    @Test
    void testGetName(){
        Tile test1 = new Tile("correct", "None", 0);
        assertTrue(test1.getName().equals("correct"));
        assertFalse(test1.getName().equals(""));

        Deed test2 = new Deed(60, 0, 2,  10, 30, 90, 160, 250, 30, 50, 50, "Mediterranean Ave.",  1);
        assertTrue(test2.getName().equals("Mediterranean Ave."));
        assertFalse(test2.getName().equals(""));

        RailRoad test3 = new RailRoad("Reading Railroad", 5);
        assertTrue(test3.getName().equals("Reading Railroad"));
        assertFalse(test3.getName().equals(""));

        Utility test4 = new Utility("Electric Company" ,12);
        assertTrue(test4.getName().equals("Electric Company"));
        assertFalse(test4.getName().equals(""));

        IncomeTax test5 = new IncomeTax("Income Tax", "IncomeTax", 4);
        assertTrue(test5.getName().equals("Income Tax"));
        assertFalse(test5.getName().equals(""));

        LuxuryTax test6 = new LuxuryTax("Luxury Tax", "LuxuryTax", 38);
        assertTrue(test6.getName().equals("Luxury Tax"));
        assertFalse(test6.getName().equals(""));

    }

    @Test
    void testGetType(){
        Tile test1 = new Tile("correct", "None", 0);
        assertTrue(test1.getType().equals("None"));
        assertFalse(test1.getType().equals(""));

        Deed test2 = new Deed(60, 0, 2,  10, 30, 90, 160, 250, 30, 50, 50, "Mediterranean Ave.",  1);
        assertTrue(test2.getType().equals("Deed"));
        assertFalse(test2.getType().equals(""));

        RailRoad test3 = new RailRoad("Reading Railroad", 5);
        assertTrue(test3.getType().equals("RailRoad"));
        assertFalse(test3.getType().equals(""));

        Utility test4 = new Utility("Electric Company" ,12);
        assertTrue(test4.getType().equals("Utility"));
        assertFalse(test4.getType().equals(""));

        IncomeTax test5 = new IncomeTax("Income Tax", "IncomeTax", 4);
        assertTrue(test5.getType().equals("IncomeTax"));
        assertFalse(test5.getType().equals(""));

        LuxuryTax test6 = new LuxuryTax("Luxury Tax", "LuxuryTax", 38);
        assertTrue(test6.getType().equals("LuxuryTax"));
        assertFalse(test6.getType().equals(""));

    }

    @Test
    void testGetPosition(){
        Tile test1 = new Tile("correct", "None", 0);
        assertTrue(test1.getPosition() == 0);
        assertFalse(test1.getPosition() == 1);

        Deed test2 = new Deed(60, 0, 2,  10, 30, 90, 160, 250, 30, 50, 50, "Mediterranean Ave.",  1);
        assertTrue(test2.getPosition() == 1);
        assertFalse(test2.getPosition() == 0);

        RailRoad test3 = new RailRoad("Reading Railroad", 5);
        assertTrue(test3.getPosition() == 5);
        assertFalse(test3.getPosition() == 1);

        Utility test4 = new Utility("Electric Company" ,12);
        assertTrue(test4.getPosition() == 12);
        assertFalse(test4.getPosition() == 5);

        IncomeTax test5 = new IncomeTax("Income Tax", "IncomeTax", 4);
        assertTrue(test5.getPosition() == 4);
        assertFalse(test5.getPosition() == 12);

        LuxuryTax test6 = new LuxuryTax("Luxury Tax", "LuxuryTax", 38);
        assertTrue(test6.getPosition() == 38);
        assertFalse(test6.getPosition() == 4);

    }
}
