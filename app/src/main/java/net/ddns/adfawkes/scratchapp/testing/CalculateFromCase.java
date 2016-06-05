package net.ddns.adfawkes.scratchapp.testing;

import net.ddns.adfawkes.scratchapp.IPiCalculator;
import net.ddns.adfawkes.scratchapp.extensions.X;

public class CalculateFromCase extends PiCalculatorCase {
    private final int n;
    private final int digits;

    public CalculateFromCase(String expected, int n, int digits) {
        super(getDescription(n, digits), expected);
        this.n = n;
        this.digits = digits;
    }

    private static String getDescription(int n, int digits) {
        return "CalculateFrom.isCorrect(" + X.str(n) + ", " + X.str(digits) + ")";
    }

    public String calculate(IPiCalculator calculator) {
        return calculator.calculateFrom(n, digits).toPlainString();
    }
}
