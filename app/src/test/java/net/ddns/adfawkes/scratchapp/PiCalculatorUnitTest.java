package net.ddns.adfawkes.scratchapp;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PiCalculatorUnitTest {
    public PiCalculator calculator = new PiCalculator();

    @Test
    public void addition_isCorrectTo1() throws Exception {
        assertEquals("3", calculator.calculateTo(1).toString());
    }

    @Test
    public void addition_isCorrectTo2() throws Exception {
        assertEquals("3.1", calculator.calculateTo(2).toString());
    }

    @Test
    public void addition_isCorrectTo3() throws Exception {
        assertEquals("3.14", calculator.calculateTo(3).toString());
    }

    @Test
    public void addition_isCorrectTo50() throws Exception {
        String expected = "3.1415926535897932384626433832795028841971693993751";
        assertEquals(expected, calculator.calculateTo(50).toString());
    }
}
