package com.gmail.fawkes.alex.scrollpi.math.testing;

import com.gmail.fawkes.alex.scrollpi.math.PiCalculator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Custom test runner for PiCalculator tests. Since all the cases get
 * combined into a single framework case, this collects exception messages
 * to identify all failed tests.
 */
public class PiCalculatorRunner {
    private final PiCalculator calculator;

    public PiCalculatorRunner(final PiCalculator calculator) {
        this.calculator = calculator;
    }

    public void run(final Collection<PiCalculatorCase> cases) throws TestFailedException {
        final List<CaseFailedException> exceptions = new ArrayList<>();
        for (final PiCalculatorCase c : cases) {
            try {
                c.test(calculator);
            } catch (final CaseFailedException e) {
                exceptions.add(e);
            }
        }

        if (!exceptions.isEmpty()) {
            throw new TestFailedException(exceptions);
        }
    }
}
