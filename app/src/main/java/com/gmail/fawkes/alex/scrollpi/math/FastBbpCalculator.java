package com.gmail.fawkes.alex.scrollpi.math;

import java.math.BigDecimal;

/**
 * BBP calculator over JNI bindings.
 */
public class FastBbpCalculator implements PiCalculator {
    public BigDecimal calculateTo(final int digits) {
        return new BigDecimal(NativeBbp.calculateTo(digits));
    }

    // TODO: "n" should be "index"... does this match algorithm descriptions?
    public BigDecimal calculateFrom(final int n, final int digits) {
        return new BigDecimal(NativeBbp.calculateFrom(n, digits));
    }

    // TODO: remove single letter variables
    public BigDecimal calculateDigitsFrom(final int n, final int digits) {
        return new BigDecimal(NativeBbp.calculateDigitsFrom(n, digits));
    }
}
