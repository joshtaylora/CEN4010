import Models.LuxuryTax;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class LuxuryTaxTest {

    @Test
    void testGetTax (){
        LuxuryTax test = new LuxuryTax("Luxury Tax", "LuxuryTax", 38);
        int tax = 75;
        assertEquals(test.getTax(), tax);
    }
}
