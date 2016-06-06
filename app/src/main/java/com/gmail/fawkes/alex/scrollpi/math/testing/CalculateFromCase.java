package com.gmail.fawkes.alex.scrollpi.math.testing;

import com.gmail.fawkes.alex.scrollpi.math.IPiCalculator;
import com.gmail.fawkes.alex.scrollpi.utilities.U;

public class CalculateFromCase extends PiCalculatorCase {
    private final int n;
    private final int digits;

    public CalculateFromCase(String expected, int n, int digits) {
        super(getDescription(n, digits), expected);
        this.n = n;
        this.digits = digits;
    }

    private static String getDescription(int n, int digits) {
        return "CalculateFrom.isCorrect(" + U.str(n) + ", " + U.str(digits) + ")";
    }

    public String calculate(IPiCalculator calculator) {
        return calculator.calculateFrom(n, digits).toPlainString();
    }
}
