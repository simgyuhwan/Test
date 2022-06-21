package tdd_test.demo.dollar;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tdd_test.demo.dollar.Dollar;

import static org.junit.jupiter.api.Assertions.*;

public class DollarTest {

    @Test
    public void testMultiplication(){
        Money five = Money.dollar(5);
        assertEquals(Money.dollar(10), five.times(2));
        assertEquals(Money.dollar(15), five.times(3));
    }

    @Test
    public void testEquality(){
        assertTrue(Money.dollar(5).equals(Money.dollar(5)));
        assertFalse(Money.dollar(5).equals(Money.dollar(6)));
    }

    @Test
    public void testFrancMultiplication(){
        Franc five = new Franc(5);
        assertEquals(new Franc(10), five.times(2));
        assertEquals(new Franc(15), five.times(3));
    }


}
