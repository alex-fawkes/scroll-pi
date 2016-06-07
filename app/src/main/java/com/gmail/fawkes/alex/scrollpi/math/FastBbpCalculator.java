package com.gmail.fawkes.alex.scrollpi.math;

import java.math.BigDecimal;

/**
 * BBP calculator over JNI bindings.
 */
public class FastBbpCalculator implements PiCalculator {
    public BigDecimal calculate(final int digits) {
        return new BigDecimal(NativeBbpAlgorithm.calculate(digits));
    }

    public BigDecimal calculateFrom(final int index, final int digits) {
        return new BigDecimal(NativeBbpAlgorithm.calculateFrom(index, digits));
    }

    public BigDecimal calculateDigitsFrom(final int index, final int digits) {
        return new BigDecimal(NativeBbpAlgorithm.calculateDigitsFrom(index, digits));
    }
}
