package com.gmail.fawkes.alex.scrollpi.math;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public abstract class BaseCalculator implements PiCalculator {
    private MathContext context = MathContext.DECIMAL128;

    @Override
    public final BigDecimal calculate(final int digits) {
        calibrate(digits);
        return pi(digits);
    }

    @Override
    public final BigDecimal calculateFrom(final int n, final int digits) {
        calibrate(digits);
        return mod(calculate(n + digits), mask(n));
    }

    @Override
    public final BigDecimal calculateDigitsFrom(final int n, final int digits) {
        calibrate(digits);
        final BigDecimal scale = big(10).pow(n + digits - 1);
        return calculateFrom(n, digits).multiply(scale).stripTrailingZeros();
    }

    protected abstract BigDecimal calculateAddend(BigDecimal n);

    protected abstract BigDecimal scaleSum(BigDecimal n);

    BigDecimal big(final int n) {
        return new BigDecimal(n);
    }

    BigDecimal invert(final BigDecimal n) {
        return big(1).divide(n, context);
    }

    /**
     * Increase the precision of the math context as more digits are requested.
     * This behavior is "sticky" in that the precision is never decreased.
     */
    private void calibrate(final int digits) {
        final int precision = context.getPrecision();
        if (digits > precision) {
            context = new MathContext(digits, RoundingMode.HALF_EVEN);
        }
    }

    private BigDecimal mod(final BigDecimal n, final BigDecimal divisor) {
        return n.divideAndRemainder(divisor, context)[1];
    }

    private BigDecimal round(final BigDecimal n, final int digits) {
        if (digits < 1) {
            return n;
        }
        return n.setScale(digits - 1, RoundingMode.DOWN);
    }

    private BigDecimal mask(final int digits) {
        if (digits < 1) {
            return big(10);
        }
        return invert(big(10).pow(digits - 1));
    }

    private BigDecimal pi(final int n) {
        BigDecimal sum = big(0);
        for (int i = 0; i < n; ++i) {
            sum = sum.add(calculateAddend(big(i)));
        }
        return round(scaleSum(sum), n);
    }
}
