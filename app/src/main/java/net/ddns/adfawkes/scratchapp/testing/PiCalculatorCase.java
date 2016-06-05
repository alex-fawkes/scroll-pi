package net.ddns.adfawkes.scratchapp.testing;

import net.ddns.adfawkes.scratchapp.IPiCalculator;

public abstract class PiCalculatorCase implements IPiCalculatorCase {
    private final String description;
    private final String expected;

    public PiCalculatorCase(String description, String expected) {
        this.description = description;
        this.expected = expected;
    }

    public abstract String calculate(IPiCalculator calculator);

    public void test(IPiCalculator calculator) throws CaseFailedException {
        final String actual = calculate(calculator);
        if (!actual.equals(expected)) {
            throw new CaseFailedException(description, expected, actual);
        }
    }
}
