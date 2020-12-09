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

        Deed test2 = new Deed("Mediterranean Ave.",
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
        assertEquals("Mediterranean Ave.", test2.getName());
        assertNotEquals("", test2.getName());

        RailRoad test3 = new RailRoad("Reading Railroad", 5);
        assertEquals("Reading Railroad", test3.getName());
        assertNotEquals("", test3.getName());

        Utility test4 = new Utility("Electric Company" ,12);
        assertEquals("Electric Company", test4.getName());
        assertNotEquals("", test4.getName());

        IncomeTax test5 = new IncomeTax("Income Tax", "IncomeTax", 4);
        assertEquals("Income Tax", test5.getName());
        assertNotEquals("", test5.getName());

        LuxuryTax test6 = new LuxuryTax("Luxury Tax", "LuxuryTax", 38);
        assertEquals("Luxury Tax", test6.getName());
        assertNotEquals("", test6.getName());

    }

    @Test
    void testGetType(){
        Tile test1 = new Tile("correct", "None", 0);
        assertEquals("None", test1.getType());
        assertNotEquals("", test1.getType());

        Deed test2 = new Deed("Mediterranean Ave.",
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
        assertEquals("Deed", test2.getType());
        assertNotEquals("", test2.getType());

        RailRoad test3 = new RailRoad("Reading Railroad", 5);
        assertEquals("RailRoad", test3.getType());
        assertNotEquals("", test3.getType());

        Utility test4 = new Utility("Electric Company" ,12);
        assertEquals("Utility", test4.getType());
        assertNotEquals("", test4.getType());

        IncomeTax test5 = new IncomeTax("Income Tax", "IncomeTax", 4);
        assertEquals("IncomeTax", test5.getType());
        assertNotEquals("", test5.getType());

        LuxuryTax test6 = new LuxuryTax("Luxury Tax", "LuxuryTax", 38);
        assertEquals("LuxuryTax", test6.getType());
        assertNotEquals("", test6.getType());

    }

    @Test
    void testGetPosition(){
        Tile test1 = new Tile("correct", "None", 0);
        assertEquals(0, test1.getPosition());
        assertNotEquals(1, test1.getPosition());

        Deed test2 = new Deed("Mediterranean Ave.",
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
        assertEquals(1, test2.getPosition());
        assertNotEquals(0, test2.getPosition());

        RailRoad test3 = new RailRoad("Reading Railroad", 5);
        assertEquals(5, test3.getPosition());
        assertNotEquals(1, test3.getPosition());

        Utility test4 = new Utility("Electric Company" ,12);
        assertEquals(12, test4.getPosition());
        assertNotEquals(5, test4.getPosition());

        IncomeTax test5 = new IncomeTax("Income Tax", "IncomeTax", 4);
        assertEquals(4, test5.getPosition());
        assertNotEquals(12, test5.getPosition());

        LuxuryTax test6 = new LuxuryTax("Luxury Tax", "LuxuryTax", 38);
        assertEquals(38, test6.getPosition());
        assertNotEquals(4, test6.getPosition());

    }
}
