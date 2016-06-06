package com.gmail.fawkes.alex.scrollpi.math.testing;

import com.gmail.fawkes.alex.scrollpi.math.PiCalculator;

import static com.gmail.fawkes.alex.scrollpi.utilities.Utilities.string;

public class CalculateFromCase extends EqualityCase {
    private final int n;
    private final int digits;

    public CalculateFromCase(final String expected, final int n, final int digits) {
        super(getDescription(n, digits), expected);
        this.n = n;
        this.digits = digits;
    }

    private static String getDescription(final int n, final int digits) {
        return "CalculateFrom.isCorrect(" + string(n) + ", " + string(digits) + ")";
    }

    public String calculate(final PiCalculator calculator) {
        return calculator.calculateFrom(n, digits).toPlainString();
    }
}
