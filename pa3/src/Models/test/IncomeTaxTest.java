package tests;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;
public class IncomeTaxTest {

    @Test
    void testGetTax (){
        IncomeTax test = new IncomeTax("Income Tax", "IncomeTax", 4);
        int tax = 200;
        assertEquals(test.getTax(), tax);
    }
}
