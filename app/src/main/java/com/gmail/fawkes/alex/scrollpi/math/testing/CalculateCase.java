package com.gmail.fawkes.alex.scrollpi.math.testing;

import com.gmail.fawkes.alex.scrollpi.math.PiCalculator;

import static com.gmail.fawkes.alex.scrollpi.utilities.Utilities.string;

public class CalculateCase extends EqualityCase {
    private final int index;

    public CalculateCase(final String expected, final int index) {
        super(getDescription(index), expected);
        this.index = index;
    }

    private static String getDescription(int index) {
        return "Calculate.isCorrect(" + string(index) + ")";
    }

    public String calculate(final PiCalculator calculator) {
        return calculator.calculate(index).toPlainString();
    }
}
