package tests;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;
public class LuxuryTaxTest {

    @Test
    void testGetTax (){
        LuxuryTax test = new LuxuryTax("Luxury Tax", "LuxuryTax", 38);
        int tax = 75;
        assertEquals(test.getTax(), tax);
    }
}
