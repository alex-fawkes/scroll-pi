package com.gmail.fawkes.alex.scrollpi.math.testing;

import com.gmail.fawkes.alex.scrollpi.math.PiCalculator;
import com.gmail.fawkes.alex.scrollpi.utilities.U;

public class CalculateToCase extends EqualityCase {
    private final int n;

    public CalculateToCase(final String expected, final int n) {
        super(getDescription(n), expected);
        this.n = n;
    }

    private static String getDescription(int n) {
        return "CalculateTo.isCorrect(" + U.string(n) + ")";
    }

    public String calculate(final PiCalculator calculator) {
        return calculator.calculateTo(n).toPlainString();
    }
}
