package tdd_test.demo.dollar;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tdd_test.demo.dollar.Dollar;

import static org.junit.jupiter.api.Assertions.*;

public class DollarTest {

    @Test
    public void testMultiplication(){
        Dollar five = new Dollar(5);
        assertEquals(new Dollar(10), five.times(2));
        assertEquals(new Dollar(15), five.times(3));
    }

    @Test
    public void testEquality(){
        assertTrue(new Dollar(5).equals(new Dollar(5)));
        assertFalse(new Dollar(5).equals(new Dollar(6)));
    }

    @Test
    public void testFrancMultiplication(){
        Franc five = new Franc(5);
        assertEquals(new Franc(10), five.times(2));
        assertEquals(new Franc(15), five.times(3));
    }


}
