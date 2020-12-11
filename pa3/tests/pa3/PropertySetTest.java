package pa3;

import Models.Deed;
import Models.PropertySet;
import Models.Board;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PropertySetTest {
    Board testBoard = new Board();

    @Test
    void addProperty() {
        PropertySet testProperty = new PropertySet(2);
        Deed testDeed2 = new Deed("Mediterranean Ave.",60, 0, 2, 10, 30, 90,160,250,30,50,50, 1);
        testProperty.addProperty(testDeed2);
        boolean testProp;
        if(testProperty.getCurrentNumProperties() == 1){
            testProp = true;
        }
        else{
            testProp = false;

        }
        assertTrue(testProp);
    }

    @Test
    void searchProperties() {
        PropertySet testProperty = new PropertySet(2);
        Deed test = new Deed("Mediterranean Ave.",60,
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
        testProperty.addProperty(test);
        boolean testProp;
        assertTrue(testProperty.searchProperties(test));
    }

    @Test
    void removeProperty() {
        PropertySet testProperty = new PropertySet(2);
        Deed testDeed1 = new Deed("Baltic Ave.", 60, 0, 4, 20, 60, 180, 320, 450, 30, 50, 50,  3);
        Deed testDeed2 = new Deed("Mediterranean Ave.",60, 0, 2, 10, 30, 90,160,250,30,50,50, 1);
        testProperty.addProperty(testDeed1);
        testProperty.addProperty(testDeed2);
        testProperty.removeProperty(testDeed1);
        boolean testProp;
        if(testProperty.getCurrentNumProperties() == 1){
            testProp = true;
        }
        else{
            testProp = false;
        }
        assertTrue(testProp);
    }

    @Test
    void checkMonopoly() {
        PropertySet testProperty = new PropertySet(2);
        Deed testDeed1 = new Deed("Baltic Ave.",60, 0, 4, 20, 60, 180, 320, 450, 30, 50, 50,  3);
        Deed testDeed2 = new Deed("Mediterranean Ave.",60, 0, 2, 10, 30, 90,160,250,30,50,50, 1);
        testProperty.addProperty(testDeed1);
        testProperty.addProperty(testDeed2);
        assertTrue(testProperty.checkMonopoly());

    }


}