package tdd_test.demo.dollar;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FrancTest {

    @Test
    public void testEquality(){
        assertTrue(Money.dollar(5).equals(Money.dollar(5)));
        assertFalse(Money.dollar(5).equals(Money.dollar(6)));
        assertTrue(Money.franc(5).equals(Money.dollar(5)));
        assertTrue(Money.franc(5).equals(Money.franc(5)));
        assertFalse(Money.franc(5).equals(Money.franc(6)));
    }

    @Test
    public void testFrancMultiplication(){
        Money five = Money.franc(5);
        Assertions.assertEquals(Money.franc(10), five.times(2));
        Assertions.assertEquals(Money.franc(15), five.times(3));
    }
}
