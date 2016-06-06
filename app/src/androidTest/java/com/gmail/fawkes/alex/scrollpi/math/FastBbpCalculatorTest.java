// TODO: rename this project something other than scratchapp
package com.gmail.fawkes.alex.scrollpi.math;

import android.app.Application;
import android.test.ApplicationTestCase;
import android.test.suitebuilder.annotation.MediumTest;

import com.gmail.fawkes.alex.scrollpi.math.testing.PiCalculatorCaseSet;
import com.gmail.fawkes.alex.scrollpi.math.testing.PiCalculatorRunner;

/**
 * This is an application test rather than unit test because we need native library access.
 */
public class FastBbpCalculatorTest extends ApplicationTestCase<Application> {
    private static final PiCalculatorRunner runner = getRunner();

    public FastBbpCalculatorTest() {
        super(Application.class);
    }

    private static PiCalculatorRunner getRunner() {
        return new PiCalculatorRunner(new FastBbpCalculator());
    }

    @MediumTest
    public void testAllCases() throws Exception {
        runner.run(PiCalculatorCaseSet.getAllCases());
    }
}
