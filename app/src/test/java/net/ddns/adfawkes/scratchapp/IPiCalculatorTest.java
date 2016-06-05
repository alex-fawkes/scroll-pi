package net.ddns.adfawkes.scratchapp;

import junit.framework.Assert;

import org.junit.Ignore;
import org.junit.Test;

@Ignore("Base class for all IPiCalculator unit tests.")
public abstract class IPiCalculatorTest {
    private final IPiCalculator calculator;

    public IPiCalculatorTest() {
        calculator = getCalculator();
    }

    public abstract IPiCalculator getCalculator();

    @Test
    public void calculateTo_isCorrectTo1() throws Exception {
        Assert.assertEquals("3", calculator.calculateTo(1).toPlainString());
    }

    @Test
    public void calculateTo_isCorrectTo2() throws Exception {
        Assert.assertEquals("3.1", calculator.calculateTo(2).toPlainString());
    }

    @Test
    public void calculateTo_isCorrectTo3() throws Exception {
        Assert.assertEquals("3.14", calculator.calculateTo(3).toPlainString());
    }

    @Test
    public void calculateTo_isCorrectTo50() throws Exception {
        String expected = "3.1415926535897932384626433832795028841971693993751";
        Assert.assertEquals(expected, calculator.calculateTo(50).toPlainString());
    }

    @Test
    public void calculateFrom_isCorrect3From0() throws Exception {
        Assert.assertEquals("3.14", calculator.calculateFrom(0, 3).toPlainString());
    }

    @Test
    public void calculateFrom_isCorrect3From1() throws Exception {
        Assert.assertEquals("0.141", calculator.calculateFrom(1, 3).toPlainString());
    }

    @Test
    public void calculateFrom_isCorrect3From2() throws Exception {
        Assert.assertEquals("0.0415", calculator.calculateFrom(2, 3).toPlainString());
    }

    @Test
    public void calculateFrom_isCorrect25From0() throws Exception {
        String expected = "3.141592653589793238462643";
        Assert.assertEquals(expected, calculator.calculateFrom(0, 25).toPlainString());
    }

    @Test
    public void calculateFrom_isCorrect25From25() throws Exception {
        String expected = "0.0000000000000000000000003832795028841971693993751";
        Assert.assertEquals(expected, calculator.calculateFrom(25, 25).toPlainString());
    }

    @Test
    public void calculateDigitsFrom_isCorrect3From0() throws Exception {
        Assert.assertEquals("314", calculator.calculateDigitsFrom(0, 3).toPlainString());
    }

    @Test
    public void calculateDigitsFrom_isCorrect3From1() throws Exception {
        Assert.assertEquals("141", calculator.calculateDigitsFrom(1, 3).toPlainString());
    }

    @Test
    public void calculateDigitsFrom_isCorrect3From2() throws Exception {
        Assert.assertEquals("415", calculator.calculateDigitsFrom(2, 3).toPlainString());
    }

    @Test
    public void calculateDigitsFrom_isCorrectFrom25To50() throws Exception {
        String expected = "3832795028841971693993751";
        Assert.assertEquals(expected, calculator.calculateDigitsFrom(25, 25).toPlainString());
    }
}
