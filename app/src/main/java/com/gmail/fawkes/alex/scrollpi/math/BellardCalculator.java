package com.gmail.fawkes.alex.scrollpi.math;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 * Calculates pi to an arbitrary number of digits using Bellard's formula:
 * <p/>
 * <pre>
 * pi = scale * sigma(n = 0, inf, addend(n))
 *
 * scale = 1 / 2^6
 * addend(n) = left(n) + right(n)
 *
 * left(n) = (-1)^n / 2^(10n)
 *
 * right(n) = right0(n) + right1(n) + ... + right6(n)
 *
 * right0(n) = -2^5 / ( 4n + 1)
 * right1(n) = -1   / ( 4n + 3)
 * right2(n) =  2^8 / (10n + 1)
 * right3(n) = -2^6 / (10n + 3)
 * right4(n) = -2^2 / (10n + 5)
 * right5(n) = -2^2 / (10n + 7)
 * right6(n) =  1   / (10n + 9)
 * </pre>
 * <p/>
 * Wikipedia has this as 40% faster than Bailey–Borwein–Plouffe, but unlike BBP
 * it is not able to calculate digits independently of previous digits.
 */
public class BellardCalculator implements IPiCalculator {
    private MathContext context = MathContext.DECIMAL128;

    @Override
    public BigDecimal calculateTo(final int digits) {
        adjustPrecision(digits);

        BigDecimal pi = big(0);
        for (int i = 0; i < digits; ++i) {
            pi = pi.add(calculateAddend(i));
        }
        return round(pi.multiply(calculateScale()), digits);
    }

    @Override
    public BigDecimal calculateFrom(final int n, final int digits) {
        return mod(calculateTo(n + digits), mask(n));
    }

    @Override
    public BigDecimal calculateDigitsFrom(final int n, final int digits) {
        final BigDecimal scale = big(10).pow(n + digits - 1);
        return calculateFrom(n, digits).multiply(scale).stripTrailingZeros();
    }

    private BigDecimal calculateScale() {
        return big(1).divide(big(2).pow(6), context);
    }

    private BigDecimal calculateLeft(final int n) {
        return big(1).negate().pow(n).divide(big(2).pow(10).pow(n), context);
    }

    private BigDecimal calculateRight(final BigDecimal n) {
        return calculateRight6(n).add(
                calculateRight5(n).add(calculateRight4(n).add(
                        calculateRight3(n).add(calculateRight2(n).add(
                                calculateRight1(n).add(calculateRight0(n)))))));
    }

    private BigDecimal calculateRight0(final BigDecimal n) {
        return big(2).pow(5).divide(big(4).multiply(n).add(big(1)), context).negate();
    }

    private BigDecimal calculateRight1(final BigDecimal n) {
        return big(1).divide(big(4).multiply(n).add(big(3)), context).negate();
    }

    private BigDecimal calculateRight2(final BigDecimal n) {
        return big(2).pow(8).divide(big(10).multiply(n).add(big(1)), context);
    }

    private BigDecimal calculateRight3(final BigDecimal n) {
        return big(2).pow(6).divide(big(10).multiply(n).add(big(3)), context).negate();
    }

    private BigDecimal calculateRight4(final BigDecimal n) {
        return big(2).pow(2).divide(big(10).multiply(n).add(big(5)), context).negate();
    }

    private BigDecimal calculateRight5(final BigDecimal n) {
        return big(2).pow(2).divide(big(10).multiply(n).add(big(7)), context).negate();
    }

    private BigDecimal calculateRight6(final BigDecimal n) {
        return big(1).divide(big(10).multiply(n).add(big(9)), context);
    }

    private BigDecimal calculateAddend(final int n) {
        return calculateLeft(n).multiply(calculateRight(big(n)));
    }

    private BigDecimal big(final int n) {
        return new BigDecimal(n);
    }

    private BigDecimal mod(final BigDecimal n, final BigDecimal divisor) {
        return n.divideAndRemainder(divisor, context)[1];
    }

    private BigDecimal invert(final BigDecimal n) {
        return big(1).divide(n, context);
    }

    private BigDecimal round(final BigDecimal n, final int digits) {
        if (digits < 1) return n;
        return n.setScale(digits - 1, RoundingMode.DOWN);
    }

    private BigDecimal mask(final int digits) {
        if (digits < 1) return big(10);
        return invert(big(10).pow(digits - 1));
    }

    private void adjustPrecision(final int digits) {
        final int precision = context.getPrecision();
        if (digits > precision) {
            context = new MathContext(digits, RoundingMode.HALF_EVEN);
        }
    }
}
