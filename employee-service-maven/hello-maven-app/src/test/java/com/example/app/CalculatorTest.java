
package com.example.app;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {

    @Test
    public void testAdd() {
        Calculator c = new Calculator();
        assertEquals(5, c.add(2, 3));
    }

    @Test
    public void testMultiply() {
        Calculator c = new Calculator();
        assertEquals(12, c.multiply(3, 4));
    }
}
