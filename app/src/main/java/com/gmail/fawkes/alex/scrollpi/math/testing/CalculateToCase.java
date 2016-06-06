package com.gmail.fawkes.alex.scrollpi.math.testing;

import com.gmail.fawkes.alex.scrollpi.math.IPiCalculator;
import com.gmail.fawkes.alex.scrollpi.utilities.U;

public class CalculateToCase extends PiCalculatorCase {
    private final int n;

    public CalculateToCase(final String expected, final int n) {
        super(getDescription(n), expected);
        this.n = n;
    }

    private static String getDescription(int n) {
        return "CalculateTo.isCorrect(" + U.str(n) + ")";
    }

    public String calculate(final IPiCalculator calculator) {
        return calculator.calculateTo(n).toPlainString();
    }
}
