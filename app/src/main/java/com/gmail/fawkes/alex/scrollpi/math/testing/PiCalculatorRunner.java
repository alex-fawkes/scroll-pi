package com.gmail.fawkes.alex.scrollpi.math.testing;

import com.gmail.fawkes.alex.scrollpi.math.IPiCalculator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Custom test runner for PiCalculator tests. Since all the cases get
 * combined into a single framework case, this collects exception messages
 * to identify all failed tests.
 */
public class PiCalculatorRunner {
    private final IPiCalculator calculator;

    public PiCalculatorRunner(final IPiCalculator calculator) {
        this.calculator = calculator;
    }

    public void run(final Collection<IPiCalculatorCase> cases) throws TestFailedException {
        final List<CaseFailedException> exceptions = new ArrayList<>();
        for (final IPiCalculatorCase c : cases) {
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
