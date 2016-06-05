package net.ddns.adfawkes.scratchapp;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;


/**
 * Implementation of Bailey–Borwein–Plouffe formula for calculating pi to
 * an arbitrary number of digits. BBP is a spigot algorithm, meaning it
 * can calculate any digit of pi without calculating preceding digits.
 */
// TODO: deduplicate versus bellard, native bbp
// TODO: general cleanup
public class BbpCalculator implements IPiCalculator {
    // TODO: adjustable precision
    private final MathContext context = new MathContext(1024, RoundingMode.HALF_EVEN);

    @Override
    public BigDecimal calculateTo(int digits) {
        return pi(digits);
    }

    @Override
    public BigDecimal calculateFrom(int n, int digits) {
        // TODO: make spigot
        return round(pi(n + digits).subtract(pi(n)), n + digits);
    }

    @Override
    public BigDecimal calculateDigitsFrom(int n, int digits) {
        return calculateFrom(n, digits).multiply(big(10).pow(n + digits - 1)).stripTrailingZeros();
    }

    private BigDecimal pi(int n) {
        BigDecimal pi = big(0);
        for (int i = 0; i < n; ++i) {
            pi = pi.add(addend(big(i)));
        }
        return round(pi, n);
    }

    private BigDecimal addend(BigDecimal n) {
        return left(n).multiply(right(n));
    }

    private BigDecimal left(BigDecimal n) {
        return invert(big(16).pow(n.intValue()));
    }

    private BigDecimal right(BigDecimal n) {
        return right3(n).add(right2(n).add(right1(n).add(right0(n))));
    }

    private BigDecimal right0(BigDecimal n) {
        return big(4).multiply(invert(big(8).multiply(n).add(big(1))));
    }

    private BigDecimal right1(BigDecimal n) {
        return big(2).multiply(invert(big(8).multiply(n).add(big(4)))).negate();
    }

    private BigDecimal right2(BigDecimal n) {
        return invert(big(8).multiply(n).add(big(5))).negate();
    }

    private BigDecimal right3(BigDecimal n) {
        return invert(big(8).multiply(n).add(big(6))).negate();
    }

    private BigDecimal big(int n) {
        return new BigDecimal(n);
    }

    private BigDecimal invert(BigDecimal n) {
        return big(1).divide(n, context);
    }

    private BigDecimal round(BigDecimal n, int digits) {
        if (digits < 1) return n;
        return n.setScale(digits - 1, RoundingMode.DOWN);
    }
}
