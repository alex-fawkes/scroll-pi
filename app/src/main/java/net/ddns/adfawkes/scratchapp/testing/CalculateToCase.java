package net.ddns.adfawkes.scratchapp.testing;

import net.ddns.adfawkes.scratchapp.IPiCalculator;
import net.ddns.adfawkes.scratchapp.extensions.X;

public class CalculateToCase extends PiCalculatorCase {
    private final int n;

    public CalculateToCase(String expected, int n) {
        super(getDescription(n), expected);
        this.n = n;
    }

    private static String getDescription(int n) {
        return "CalculateTo.isCorrect(" + X.str(n) + ")";
    }

    // TODO: final everywhere
    public String calculate(IPiCalculator calculator) {
        return calculator.calculateTo(n).toPlainString();
    }
}
