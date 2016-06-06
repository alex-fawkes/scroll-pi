package com.gmail.fawkes.alex.scrollpi.math.testing;

import com.gmail.fawkes.alex.scrollpi.math.IPiCalculator;
import com.gmail.fawkes.alex.scrollpi.utilities.U;

public class CalculateToCase extends PiCalculatorCase {
    private final int n;

    public CalculateToCase(String expected, int n) {
        super(getDescription(n), expected);
        this.n = n;
    }

    private static String getDescription(int n) {
        return "CalculateTo.isCorrect(" + U.str(n) + ")";
    }

    // TODO: final everywhere
    public String calculate(IPiCalculator calculator) {
        return calculator.calculateTo(n).toPlainString();
    }
}
