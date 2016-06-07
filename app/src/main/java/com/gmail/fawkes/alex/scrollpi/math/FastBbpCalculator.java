package com.gmail.fawkes.alex.scrollpi.math;

import java.math.BigDecimal;

/**
 * BBP calculator over JNI bindings.
 */
public class FastBbpCalculator implements PiCalculator {
    public BigDecimal calculate(final int digits) {
        return new BigDecimal(NativeBbpAlgorithm.calculate(digits));
    }

    // TODO: "n" should be "index"... does this match algorithm descriptions?
    public BigDecimal calculateFrom(final int n, final int digits) {
        return new BigDecimal(NativeBbpAlgorithm.calculateFrom(n, digits));
    }

    // TODO: remove single letter variables
    public BigDecimal calculateDigitsFrom(final int n, final int digits) {
        return new BigDecimal(NativeBbpAlgorithm.calculateDigitsFrom(n, digits));
    }
}
