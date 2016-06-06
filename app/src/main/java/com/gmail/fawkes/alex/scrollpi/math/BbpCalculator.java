package com.gmail.fawkes.alex.scrollpi.math;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;


/**
 * Implementation of Bailey–Borwein–Plouffe formula for calculating pi to
 * an arbitrary number of digits. BBP is a spigot algorithm, meaning it
 * can calculate any digit of pi without calculating preceding digits.
 * However, this is not the spigot version of the algorithm, but rather
 * a basic test implementation.
 */
// TODO: deduplicate versus bellard, native bpp_calculation
// TODO: general cleanup
public class BbpCalculator implements PiCalculator {
    // TODO: adjustable precision
    private final MathContext context = new MathContext(1024, RoundingMode.HALF_EVEN);

    @Override
    public BigDecimal calculateTo(final int digits) {
        return pi(digits);
    }

    @Override
    public BigDecimal calculateFrom(final int n, final int digits) {
        return round(pi(n + digits).subtract(pi(n)), n + digits);
    }

    @Override
    public BigDecimal calculateDigitsFrom(final int n, final int digits) {
        return calculateFrom(n, digits).multiply(big(10).pow(n + digits - 1)).stripTrailingZeros();
    }

    private BigDecimal pi(final int n) {
        BigDecimal pi = big(0);
        for (int i = 0; i < n; ++i) {
            pi = pi.add(addend(big(i)));
        }
        return round(pi, n);
    }

    private BigDecimal addend(final BigDecimal n) {
        return left(n).multiply(right(n));
    }

    private BigDecimal left(final BigDecimal n) {
        return invert(big(16).pow(n.intValue()));
    }

    private BigDecimal right(final BigDecimal n) {
        return right3(n).add(right2(n).add(right1(n).add(right0(n))));
    }

    private BigDecimal right0(final BigDecimal n) {
        return big(4).multiply(invert(big(8).multiply(n).add(big(1))));
    }

    private BigDecimal right1(final BigDecimal n) {
        return big(2).multiply(invert(big(8).multiply(n).add(big(4)))).negate();
    }

    private BigDecimal right2(final BigDecimal n) {
        return invert(big(8).multiply(n).add(big(5))).negate();
    }

    private BigDecimal right3(final BigDecimal n) {
        return invert(big(8).multiply(n).add(big(6))).negate();
    }

    private BigDecimal big(final int n) {
        return new BigDecimal(n);
    }

    private BigDecimal invert(final BigDecimal n) {
        return big(1).divide(n, context);
    }

    // TODO: add if curly braces
    // TODO: use map where appropriate
    private BigDecimal round(final BigDecimal n, final int digits) {
        if (digits < 1) {
            return n;
        }
        return n.setScale(digits - 1, RoundingMode.DOWN);
    }
}
