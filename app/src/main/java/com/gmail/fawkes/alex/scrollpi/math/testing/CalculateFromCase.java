package com.gmail.fawkes.alex.scrollpi.math.testing;

import com.gmail.fawkes.alex.scrollpi.math.PiCalculator;

import static com.gmail.fawkes.alex.scrollpi.utilities.Utilities.string;

public class CalculateFromCase extends EqualityCase {
    private final int index;
    private final int digits;

    public CalculateFromCase(final String expected, final int index, final int digits) {
        super(getDescription(index, digits), expected);
        this.index = index;
        this.digits = digits;
    }

    private static String getDescription(final int index, final int digits) {
        return "CalculateFrom.isCorrect(" + string(index) + ", " + string(digits) + ")";
    }

    public String calculate(final PiCalculator calculator) {
        return calculator.calculateFrom(index, digits).toPlainString();
    }
}
