package pa3;

import Models.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TileTests {

    //test all getters on children of Tile, as well as Tile itself

    @Test
    void testGetName(){
        Tile test1 = new Tile("correct", "None", 0);
        assertEquals("correct", test1.getName());
        assertNotEquals("", test1.getName());

        Deed test2 = new Deed(60, 0, 2,  10, 30, 90, 160, 250, 30, 50, 50, "Mediterranean Ave.",  1);
        assertEquals("Mediterranean Ave.", test1.getName());
        assertNotEquals("", test1.getName());

        RailRoad test3 = new RailRoad("Reading Railroad", 5);
        assertEquals("Reading Railroad", test1.getName());
        assertNotEquals("", test1.getName());

        Utility test4 = new Utility("Electric Company" ,12);
        assertEquals("Electric Company", test1.getName());
        assertNotEquals("", test1.getName());

        IncomeTax test5 = new IncomeTax("Income Tax", "IncomeTax", 4);
        assertEquals("Income Tax", test1.getName());
        assertNotEquals("", test1.getName());

        LuxuryTax test6 = new LuxuryTax("Luxury Tax", "LuxuryTax", 38);
        assertEquals("Luxury Tax", test1.getName());
        assertNotEquals("", test1.getName());

    }

    @Test
    void testGetType(){
        Tile test1 = new Tile("correct", "None", 0);
        assertEquals("None", test1.getType());
        assertNotEquals("", test1.getType());

        Deed test2 = new Deed(60, 0, 2,  10, 30, 90, 160, 250, 30, 50, 50, "Mediterranean Ave.",  1);
        assertEquals("Deed", test1.getType());
        assertNotEquals("", test1.getType());

        RailRoad test3 = new RailRoad("Reading Railroad", 5);
        assertEquals("RailRoad", test1.getType());
        assertNotEquals("", test1.getType());

        Utility test4 = new Utility("Electric Company" ,12);
        assertEquals("Utility", test1.getType());
        assertNotEquals("", test1.getType());

        IncomeTax test5 = new IncomeTax("Income Tax", "IncomeTax", 4);
        assertEquals("IncomeTax", test1.getType());
        assertNotEquals("", test1.getType());

        LuxuryTax test6 = new LuxuryTax("Luxury Tax", "LuxuryTax", 38);
        assertEquals("LuxuryTax", test1.getType());
        assertNotEquals("", test1.getType());

    }

    @Test
    void testGetPosition(){
        Tile test1 = new Tile("correct", "None", 0);
        assertEquals(0, test1.getPosition());
        assertNotEquals(1, test1.getPosition());

        Deed test2 = new Deed(60, 0, 2,  10, 30, 90, 160, 250, 30, 50, 50, "Mediterranean Ave.",  1);
        assertEquals(1, test1.getPosition());
        assertNotEquals(0, test1.getPosition());

        RailRoad test3 = new RailRoad("Reading Railroad", 5);
        assertEquals(5, test1.getPosition());
        assertNotEquals(1, test1.getPosition());

        Utility test4 = new Utility("Electric Company" ,12);
        assertEquals(12, test1.getPosition());
        assertNotEquals(5, test1.getPosition());

        IncomeTax test5 = new IncomeTax("Income Tax", "IncomeTax", 4);
        assertEquals(4, test1.getPosition());
        assertNotEquals(12, test1.getPosition());

        LuxuryTax test6 = new LuxuryTax("Luxury Tax", "LuxuryTax", 38);
        assertEquals(38, test1.getPosition());
        assertNotEquals(4, test1.getPosition());

    }
}
