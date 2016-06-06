package com.gmail.fawkes.alex.scrollpi.math;

import java.math.BigDecimal;

/**
 * BBP calculator over JNI bindings.
 */
public class FastBbpCalculator implements IPiCalculator {
    public BigDecimal calculateTo(int digits) {
        return new BigDecimal(NativeBbp.calculateTo(digits));
    }

    // TODO: "n" should be "index"... does this match algorithm descriptions?
    public BigDecimal calculateFrom(int n, int digits) {
        return new BigDecimal(NativeBbp.calculateFrom(n, digits));
    }

    // TODO: remove single letter variables
    public BigDecimal calculateDigitsFrom(int n, int digits) {
        return new BigDecimal(NativeBbp.calculateDigitsFrom(n, digits));
    }
}
