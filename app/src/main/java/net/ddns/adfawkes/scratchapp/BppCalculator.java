package net.ddns.adfawkes.scratchapp;

import java.math.BigDecimal;

public class BppCalculator implements IPiCalculator {
    public BigDecimal calculateTo(int digits) {
        return new BigDecimal(NativeBppCalc.calculateTo(digits));
    }

    public BigDecimal calculateFrom(int n, int digits) {
        return new BigDecimal(NativeBppCalc.calculateFrom(n, digits));
    }

    public BigDecimal calculateDigitsFrom(int n, int digits) {
        return new BigDecimal(NativeBppCalc.calculateDigitsFrom(n, digits));
    }
}
