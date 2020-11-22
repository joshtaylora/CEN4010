import Models.IncomeTax;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class IncomeTaxTest {

    @Test
    void testGetTax (){
        IncomeTax test = new IncomeTax("Income Tax", "IncomeTax", 4);
        int tax = 200;
        assertEquals(test.getTax(), tax);
    }
}
