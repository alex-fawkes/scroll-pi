package com.gmail.fawkes.alex.scrollpi.math.testing;

import com.gmail.fawkes.alex.scrollpi.math.PiCalculator;

public abstract class EqualityCase implements PiCalculatorCase {
    private final String description;
    private final String expected;

    EqualityCase(String description, String expected) {
        this.description = description;
        this.expected = expected;
    }

    protected abstract String calculate(PiCalculator calculator);

    public void test(final PiCalculator calculator) throws CaseFailedException {
        final String actual = calculate(calculator);
        if (!actual.equals(expected)) {
            throw new CaseFailedException(description, expected, actual);
        }
    }
}
