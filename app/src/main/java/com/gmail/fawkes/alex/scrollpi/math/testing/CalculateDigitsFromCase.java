package com.gmail.fawkes.alex.scrollpi.math.testing;

import com.gmail.fawkes.alex.scrollpi.math.IPiCalculator;
import com.gmail.fawkes.alex.scrollpi.utilities.U;

public class CalculateDigitsFromCase extends PiCalculatorCase {
    private final int n;
    private final int digits;

    public CalculateDigitsFromCase(String expected, int n, int digits) {
        super(getDescription(n, digits), expected);
        this.n = n;
        this.digits = digits;
    }

    private static String getDescription(int n, int digits) {
        return "CalculateDigitsFrom.isCorrect(" + U.str(n) + ", " + U.str(digits);
    }

    public String calculate(IPiCalculator calculator) {
        return calculator.calculateDigitsFrom(n, digits).toPlainString();
    }
}
