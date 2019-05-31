package edu.patdouble.simplerest;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class MoneyTest {
    @Test
    public void testConstructor() {
        Money m = new Money(BigDecimal.valueOf(10.00), "USD");
        assertEquals(BigDecimal.valueOf(10.00), m.getAmount());
        assertEquals("USD", m.getCurrency());
    }

    @Test
    public void testDefaultConstructor() {
        Money m = new Money();
        assertNull(m.getAmount());
        assertNull(m.getCurrency());
    }

    @Test
    public void testEquals() {
        Money m10a = new Money(BigDecimal.valueOf(10.00), "USD");
        Money m10b = new Money(BigDecimal.valueOf(10.00), "USD");
        Money m5a = new Money(BigDecimal.valueOf(5.00), "USD");
        Money m5b = new Money(BigDecimal.valueOf(5.00), "USD");

        assertEquals(m10a, m10a);
        assertEquals(m10a, m10b);
        assertNotEquals(m10a, m5a);
    }
}