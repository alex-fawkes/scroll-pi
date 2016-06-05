package net.ddns.adfawkes.scratchapp;

import java.math.BigDecimal;

/**
 * BBP calculator over JNI bindings.
 */
public class NativeBbpCalculator implements IPiCalculator {
    public BigDecimal calculateTo(int digits) {
        return new BigDecimal(NativeBbp.calculateTo(digits));
    }

    public BigDecimal calculateFrom(int n, int digits) {
        return new BigDecimal(NativeBbp.calculateFrom(n, digits));
    }

    public BigDecimal calculateDigitsFrom(int n, int digits) {
        return new BigDecimal(NativeBbp.calculateDigitsFrom(n, digits));
    }
}
