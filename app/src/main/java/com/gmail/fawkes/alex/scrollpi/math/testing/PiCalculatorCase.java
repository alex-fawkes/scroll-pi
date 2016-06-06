package com.gmail.fawkes.alex.scrollpi.math.testing;

import com.gmail.fawkes.alex.scrollpi.math.IPiCalculator;

public abstract class PiCalculatorCase implements IPiCalculatorCase {
    private final String description;
    private final String expected;

    PiCalculatorCase(String description, String expected) {
        this.description = description;
        this.expected = expected;
    }

    protected abstract String calculate(IPiCalculator calculator);

    public void test(IPiCalculator calculator) throws CaseFailedException {
        final String actual = calculate(calculator);
        if (!actual.equals(expected)) {
            throw new CaseFailedException(description, expected, actual);
        }
    }
}
