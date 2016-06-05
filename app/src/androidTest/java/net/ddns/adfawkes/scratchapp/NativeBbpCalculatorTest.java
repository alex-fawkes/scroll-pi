package net.ddns.adfawkes.scratchapp;

import android.app.Application;
import android.test.ApplicationTestCase;
import android.test.suitebuilder.annotation.MediumTest;

/**
 * This is an application test rather than unit test because we need native library access.
 */
public class NativeBbpCalculatorTest extends ApplicationTestCase<Application> {
    private static final IPiCalculator calculator = new NativeBbpCalculator();

    // TODO: deduplicate this against BellardPiCalculatorUnitTest
    // TODO: rename this project something other than scratchapp
    public NativeBbpCalculatorTest() {
        super(Application.class);
    }

    @MediumTest
    public void testCalculateTo_isCorrectTo1() throws Exception {
        assertEquals("3", calculator.calculateTo(1).toPlainString());
    }

    @MediumTest
    public void testCalculateTo_isCorrectTo2() throws Exception {
        assertEquals("3.1", calculator.calculateTo(2).toPlainString());
    }

    @MediumTest
    public void testCalculateTo_isCorrectTo3() throws Exception {
        assertEquals("3.14", calculator.calculateTo(3).toPlainString());
    }

    @MediumTest
    public void testCalculateTo_isCorrectTo50() throws Exception {
        String expected = "3.1415926535897932384626433832795028841971693993751";
        assertEquals(expected, calculator.calculateTo(50).toPlainString());
    }

    @MediumTest
    public void testCalculateFrom_isCorrect3From0() throws Exception {
        assertEquals("3.14", calculator.calculateFrom(0, 3).toPlainString());
    }

    // TODO: copy these to unit tests, also dedupe
    @MediumTest
    public void testCalculateFrom_isCorrect3From1() throws Exception {
        assertEquals("0.141", calculator.calculateFrom(1, 3).toPlainString());
    }

    @MediumTest
    public void testCalculateFrom_isCorrect3From2() throws Exception {
        assertEquals("0.0415", calculator.calculateFrom(2, 3).toPlainString());
    }

    @MediumTest
    public void testCalculateFrom_isCorrect25From0() throws Exception {
        String expected = "3.141592653589793238462643";
        assertEquals(expected, calculator.calculateFrom(0, 25).toPlainString());
    }

    @MediumTest
    public void testCalculateFrom_isCorrect25From25() throws Exception {
        String expected = "0.0000000000000000000000003832795028841971693993751";
        assertEquals(expected, calculator.calculateFrom(25, 25).toPlainString());
    }

    @MediumTest
    public void testCalculateDigitsFrom_isCorrect3From0() throws Exception {
        assertEquals("314", calculator.calculateDigitsFrom(0, 3).toPlainString());
    }

    @MediumTest
    public void testCalculateDigitsFrom_isCorrect3From1() throws Exception {
        assertEquals("141", calculator.calculateDigitsFrom(1, 3).toPlainString());
    }

    @MediumTest
    public void testCalculateDigitsFrom_isCorrect3From2() throws Exception {
        assertEquals("415", calculator.calculateDigitsFrom(2, 3).toPlainString());
    }

    @MediumTest
    public void testCalculateDigitsFrom_isCorrectFrom25To50() throws Exception {
        String expected = "3832795028841971693993751";
        assertEquals(expected, calculator.calculateDigitsFrom(25, 25).toPlainString());
    }
}
