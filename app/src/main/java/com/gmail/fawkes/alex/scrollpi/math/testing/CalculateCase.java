package com.gmail.fawkes.alex.scrollpi.math.testing;

import com.gmail.fawkes.alex.scrollpi.math.PiCalculator;

import static com.gmail.fawkes.alex.scrollpi.utilities.Utilities.string;

public class CalculateCase extends EqualityCase {
    private final int n;

    public CalculateCase(final String expected, final int n) {
        super(getDescription(n), expected);
        this.n = n;
    }

    private static String getDescription(int n) {
        return "Calculate.isCorrect(" + string(n) + ")";
    }

    public String calculate(final PiCalculator calculator) {
        return calculator.calculate(n).toPlainString();
    }
}
