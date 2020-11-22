package Models;

import javafx.beans.property.Property;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PropertySetTest {

    @Test
    void testAddSearchProperty() {
        //This test checks the add and search property methods
        PropertySet testPropSet = new PropertySet(3);
        Deed testDeed = new Deed(100, 1, 6, 12, 24, 48, 96, 192, 50, 50 ,50 ,"Blue", 8);

        assertFalse(testPropSet.searchProperties(testDeed));

        testPropSet.addProperty(testDeed);

        assertTrue(testPropSet.searchProperties(testDeed));
    }

    @Test
    void testRemoveProperty() {
        //This test checks the remove and search property methods
        PropertySet testPropSet = new PropertySet(3);
        Deed testDeed = new Deed(100, 1, 6, 12, 24, 48, 96, 192, 50, 50 ,50 ,"Blue", 8);
        testPropSet.addProperty(testDeed);

        assertTrue(testPropSet.searchProperties(testDeed));

        testPropSet.removeProperty(testDeed);

        assertFalse(testPropSet.searchProperties(testDeed));
    }

    @Test
    void testCheckMonopoly() {
        //This test checks the checkMonopoly method
        PropertySet testPropSet = new PropertySet(1);
        Deed testDeed = new Deed(100, 1, 6, 12, 24, 48, 96, 192, 50, 50 ,50 ,"Blue", 8);

        assertFalse(testPropSet.checkMonopoly());

        testPropSet.addProperty(testDeed);

        assertTrue(testPropSet.checkMonopoly());
    }

    @Test
    void testGetSetSetSize() {
        //This test checks the Get and Set methods for SetSize variable
        PropertySet testPropSet = new PropertySet(1);

        assertEquals(testPropSet.getSetSize(), 1);

        testPropSet.setSetSize(3);

        assertEquals(testPropSet.getSetSize(), 3);
    }

    @Test
    void testGetSetCurrentNumProperties() {
        //This test checks the Get and Set methods for CurrentNumProperties variable
        PropertySet testPropSet = new PropertySet(1);

        assertEquals(testPropSet.getCurrentNumProperties(), 0);

        testPropSet.setCurrentNumProperties(3);

        assertEquals(testPropSet.getCurrentNumProperties(), 3);
    }

    @Test
    void testGetSetPropertiesInSet() {
        //This test checks the Get and Set methods for PropertiesInSet array
        PropertySet testPropSet = new PropertySet(1);
        Deed[] testDeeds = {new Deed(100, 1, 6, 12, 24, 48, 96, 192, 50, 50 ,50 ,"Blue", 8)};

        testPropSet.setPropertiesInSet(testDeeds);

        Deed[] testResult = testPropSet.getPropertiesInSet();

        assertTrue(testResult[0].getName().compareTo("Blue") == 0);
    }
}
