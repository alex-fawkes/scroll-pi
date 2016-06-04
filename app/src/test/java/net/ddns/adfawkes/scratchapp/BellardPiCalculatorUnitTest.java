package net.ddns.adfawkes.scratchapp;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BellardPiCalculatorUnitTest {
    public IPiCalculator calculator = new BellardPiCalculator();

    @Test
    public void calculateTo_isCorrectTo1() throws Exception {
        assertEquals("3", calculator.calculateTo(1).toPlainString());
    }

    @Test
    public void calculateTo_isCorrectTo2() throws Exception {
        assertEquals("3.1", calculator.calculateTo(2).toPlainString());
    }

    @Test
    public void calculateTo_isCorrectTo3() throws Exception {
        assertEquals("3.14", calculator.calculateTo(3).toPlainString());
    }

    @Test
    public void calculateTo_isCorrectTo50() throws Exception {
        String expected = "3.1415926535897932384626433832795028841971693993751";
        assertEquals(expected, calculator.calculateTo(50).toPlainString());
    }

    @Test
    public void calculateFromTo_isCorrectFrom25To50() throws Exception {
        String expected = "0.0000000000000000000000003832795028841971693993751";
        assertEquals(expected, calculator.calculateFrom(25, 25).toPlainString());
    }

    @Test
    public void calculateDigitsFromTo_isCorrectFrom25To50() throws Exception {
        String expected = "3832795028841971693993751";
        assertEquals(expected, calculator.calculateDigitsFrom(25, 25).toPlainString());
    }
}
