package net.ddns.adfawkes.scratchapp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class IPiCalculatorTest {
    private IPiCalculator calculator;

    public IPiCalculatorTest(Class cls) throws InstantiationException, IllegalAccessException {
        calculator = (IPiCalculator)cls.newInstance();
    }

    @Parameters
    public static Collection getClassesToTest() {
        return Arrays.asList(BellardCalculator.class, BbpCalculator.class);
    }

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
    public void calculateFrom_isCorrect3From0() throws Exception {
        assertEquals("3.14", calculator.calculateFrom(0, 3).toPlainString());
    }

    @Test
    public void calculateFrom_isCorrect25From0() throws Exception {
        String expected = "3.141592653589793238462643";
        assertEquals(expected, calculator.calculateFrom(0, 25).toPlainString());
    }

    @Test
    public void calculateFrom_isCorrect25From25() throws Exception {
        String expected = "0.0000000000000000000000003832795028841971693993751";
        assertEquals(expected, calculator.calculateFrom(25, 25).toPlainString());
    }

    @Test
    public void calculateDigitsFrom_isCorrect3From0() throws Exception {
        assertEquals("314", calculator.calculateDigitsFrom(0, 3).toPlainString());
    }

    @Test
    public void calculateDigitsFrom_isCorrect3From1() throws Exception {
        assertEquals("141", calculator.calculateDigitsFrom(1, 3).toPlainString());
    }

    @Test
    public void calculateDigitsFrom_isCorrect3From2() throws Exception {
        assertEquals("415", calculator.calculateDigitsFrom(2, 3).toPlainString());
    }

    @Test
    public void calculateDigitsFrom_isCorrectFrom25To50() throws Exception {
        String expected = "3832795028841971693993751";
        assertEquals(expected, calculator.calculateDigitsFrom(25, 25).toPlainString());
    }
}
