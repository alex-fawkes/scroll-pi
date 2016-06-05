package net.ddns.adfawkes.scratchapp.testing;

import net.ddns.adfawkes.scratchapp.IPiCalculator;
import net.ddns.adfawkes.scratchapp.extensions.X;

public class CalculateDigitsFromCase extends PiCalculatorCase {
    private final int n;
    private final int digits;

    public CalculateDigitsFromCase(String expected, int n, int digits) {
        super(getDescription(n, digits), expected);
        this.n = n;
        this.digits = digits;
    }

    private static String getDescription(int n, int digits) {
        return "CalculateDigitsFrom.isCorrect(" + X.str(n) + ", " + X.str(digits);
    }

    public String calculate(IPiCalculator calculator) {
        return calculator.calculateDigitsFrom(n, digits).toPlainString();
    }
}
