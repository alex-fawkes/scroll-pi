package com.gmail.fawkes.alex.scrollpi.math.testing;

import com.gmail.fawkes.alex.scrollpi.math.PiCalculator;
import com.gmail.fawkes.alex.scrollpi.utilities.U;

public class CalculateDigitsFromCase extends EqualityCase {
    private final int n;
    private final int digits;

    public CalculateDigitsFromCase(final String expected, final int n, final int digits) {
        super(getDescription(n, digits), expected);
        this.n = n;
        this.digits = digits;
    }

    private static String getDescription(final int n, final int digits) {
        return "CalculateDigitsFrom.isCorrect(" + U.string(n) + ", " + U.string(digits);
    }

    public String calculate(final PiCalculator calculator) {
        return calculator.calculateDigitsFrom(n, digits).toPlainString();
    }
}
