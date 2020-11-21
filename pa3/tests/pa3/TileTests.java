package tests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class TileTests {

    //test all getters on children of Tile, as well as Tile itself

    @Test
    void testGetName(){
        Tile test1 = new Tile("correct", "None", 0);
        assertTrue(test1.getName().equals("correct"));
        assertFalse(test1.getName().equals(""));

        Deed test2 = new Deed(60, 0, 2,  10, 30, 90, 160, 250, 30, 50, 50, "Mediterranean Ave.",  1);
        assertTrue(test1.getName().equals("Mediterranean Ave."));
        assertFalse(test1.getName().equals(""));

        RailRoad test3 = new RailRoad("Reading Railroad", 5);
        assertTrue(test1.getName().equals("Reading Railroad"));
        assertFalse(test1.getName().equals(""));

        Utility test4 = new Utility("Electric Company" ,12);
        assertTrue(test1.getName().equals("Electric Company"));
        assertFalse(test1.getName().equals(""));

        IncomeTax test5 = new IncomeTax("Income Tax", "IncomeTax", 4);
        assertTrue(test1.getName().equals("Income Tax"));
        assertFalse(test1.getName().equals(""));

        LuxuryTax test6 = new LuxuryTax("Luxury Tax", "LuxuryTax", 38);
        assertTrue(test1.getName().equals("Luxury Tax"));
        assertFalse(test1.getName().equals(""));

    }

    @Test
    void testGetType(){
        Tile test1 = new Tile("correct", "None", 0);
        assertTrue(test1.getType().equals("None"));
        assertFalse(test1.getType().equals(""));

        Deed test2 = new Deed(60, 0, 2,  10, 30, 90, 160, 250, 30, 50, 50, "Mediterranean Ave.",  1);
        ssertTrue(test1.getType().equals("Deed"));
        assertFalse(test1.getType().equals(""));

        RailRoad test3 = new RailRoad("Reading Railroad", 5);
        ssertTrue(test1.getType().equals("RailRoad"));
        assertFalse(test1.getType().equals(""));

        Utility test4 = new Utility("Electric Company" ,12);
        ssertTrue(test1.getType().equals("Utility"));
        assertFalse(test1.getType().equals(""));

        IncomeTax test5 = new IncomeTax("Income Tax", "IncomeTax", 4);
        ssertTrue(test1.getType().equals("IncomeTax"));
        assertFalse(test1.getType().equals(""));

        LuxuryTax test6 = new LuxuryTax("Luxury Tax", "LuxuryTax", 38);
        ssertTrue(test1.getType().equals("LuxuryTax"));
        assertFalse(test1.getType().equals(""));

    }

    @Test
    void testGetPosition(){
        Tile test1 = new Tile("correct", "None", 0);
        assertTrue(test1.getPosition() == 0);
        assertFalse(test1.getPosition() == 1);

        Deed test2 = new Deed(60, 0, 2,  10, 30, 90, 160, 250, 30, 50, 50, "Mediterranean Ave.",  1);
        assertTrue(test1.getPosition() == 1);
        assertFalse(test1.getPosition() == 0);

        RailRoad test3 = new RailRoad("Reading Railroad", 5);
        assertTrue(test1.getPosition() == 5);
        assertFalse(test1.getPosition() == 1);

        Utility test4 = new Utility("Electric Company" ,12);
        assertTrue(test1.getPosition() == 12);
        assertFalse(test1.getPosition() == 5);

        IncomeTax test5 = new IncomeTax("Income Tax", "IncomeTax", 4);
        assertTrue(test1.getPosition() == 4);
        assertFalse(test1.getPosition() == 12);

        LuxuryTax test6 = new LuxuryTax("Luxury Tax", "LuxuryTax", 38);
        assertTrue(test1.getPosition() == 38);
        assertFalse(test1.getPosition() == 4);

    }
}
