package com.gmail.fawkes.alex.scrollpi.math;

import com.gmail.fawkes.alex.scrollpi.math.testing.PiCalculatorCaseSet;
import com.gmail.fawkes.alex.scrollpi.math.testing.PiCalculatorRunner;

import org.junit.Ignore;
import org.junit.Test;

@Ignore("Base class for all PiCalculator unit tests.")
public abstract class PiCalculatorTest {
    private final PiCalculatorRunner runner;

    public PiCalculatorTest() {
        runner = new PiCalculatorRunner(getCalculator());
    }

    protected abstract PiCalculator getCalculator();

    @Test
    public void testAllCases() throws Exception {
        runner.run(PiCalculatorCaseSet.getAllCases());
    }
}
