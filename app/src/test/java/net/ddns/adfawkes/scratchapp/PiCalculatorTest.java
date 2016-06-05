package net.ddns.adfawkes.scratchapp;

import net.ddns.adfawkes.scratchapp.testing.PiCalculatorCaseSet;
import net.ddns.adfawkes.scratchapp.testing.PiCalculatorRunner;

import org.junit.Ignore;
import org.junit.Test;

@Ignore("Base class for all IPiCalculator unit tests.")
public abstract class PiCalculatorTest {
    private final PiCalculatorRunner runner;

    public PiCalculatorTest() {
        runner = new PiCalculatorRunner(getCalculator());
    }

    public abstract IPiCalculator getCalculator();

    @Test
    public void testAllCases() throws Exception {
        runner.run(PiCalculatorCaseSet.getAllCases());
    }
}
