// TODO: rename this project something other than scratchapp
package net.ddns.adfawkes.scratchapp;

import android.app.Application;
import android.test.ApplicationTestCase;
import android.test.suitebuilder.annotation.MediumTest;

import net.ddns.adfawkes.scratchapp.testing.PiCalculatorCaseSet;
import net.ddns.adfawkes.scratchapp.testing.PiCalculatorRunner;

/**
 * This is an application test rather than unit test because we need native library access.
 */
public class NativeBbpCalculatorTest extends ApplicationTestCase<Application> {
    private static final PiCalculatorRunner runner = getRunner();

    public NativeBbpCalculatorTest() {
        super(Application.class);
    }

    @MediumTest
    public void testAllCases() throws Exception {
        runner.run(PiCalculatorCaseSet.getAllCases());
    }

    private static PiCalculatorRunner getRunner() {
        return new PiCalculatorRunner(new NativeBbpCalculator());
    }
}
