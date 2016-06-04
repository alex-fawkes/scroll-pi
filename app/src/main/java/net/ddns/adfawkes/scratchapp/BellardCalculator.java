package net.ddns.adfawkes.scratchapp;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 * Calculates pi to an arbitrary number of digits using Bellard's formula:
 * <p/>
 * <pre>
 * scale = 1 / 2^6
 * left(n) = (-1)^n / 2^(10n)
 *
 * right0(n) = -2^5 / ( 4n + 1)
 * right1(n) = -1   / ( 4n + 3)
 * right2(n) =  2^8 / (10n + 1)
 * right3(n) = -2^6 / (10n + 3)
 * right4(n) = -2^2 / (10n + 5)
 * right5(n) = -2^2 / (10n + 7)
 * right6(n) =  1   / (10n + 9)
 *
 * right(n) = right0(n) + right1(n) + ... + right6(n)
 *
 * addend(n) = left(n) + right(n)
 *
 * bellard(n) = scale * sigma(0, n, addend(n))
 * </pre>
 * <p/>
 * Wikipedia has this as 40% faster than Bailey–Borwein–Plouffe, but unlike BBP
 * it is not able to calculate digits independently of previous digits.
 */
public class BellardCalculator implements IPiCalculator {
    private MathContext context = MathContext.DECIMAL128;

    @Override
    public BigDecimal calculateTo(int digits) {
        adjustPrecision(digits);

        BigDecimal pi = big(0);
        for (int n = 0; n < digits; ++n) {
            pi = pi.add(calculateAddend(n));
        }
        return round(pi.multiply(calculateScale()), digits);
    }

    @Override
    public BigDecimal calculateFrom(int n, int digits) {
        return mod(calculateTo(n + digits), mask(n));
    }

    @Override
    public BigDecimal calculateDigitsFrom(int n, int digits) {
        BigDecimal scale = big(10).pow(n + digits - 1);
        return calculateFrom(n, digits).multiply(scale).stripTrailingZeros();
    }

    private BigDecimal calculateScale() {
        return big(1).divide(big(2).pow(6), context);
    }

    private BigDecimal calculateLeft(int n) {
        return big(1).negate().pow(n).divide(big(2).pow(10).pow(n), context);
    }

    private BigDecimal calculateRight(BigDecimal n) {
        return calculateRight6(n).add(
                calculateRight5(n).add(calculateRight4(n).add(
                        calculateRight3(n).add(calculateRight2(n).add(
                                calculateRight1(n).add(calculateRight0(n)))))));
    }

    private BigDecimal calculateRight0(BigDecimal n) {
        return big(2).pow(5).divide(big(4).multiply(n).add(big(1)), context).negate();
    }

    private BigDecimal calculateRight1(BigDecimal n) {
        return big(1).divide(big(4).multiply(n).add(big(3)), context).negate();
    }

    private BigDecimal calculateRight2(BigDecimal n) {
        return big(2).pow(8).divide(big(10).multiply(n).add(big(1)), context);
    }

    private BigDecimal calculateRight3(BigDecimal n) {
        return big(2).pow(6).divide(big(10).multiply(n).add(big(3)), context).negate();
    }

    private BigDecimal calculateRight4(BigDecimal n) {
        return big(2).pow(2).divide(big(10).multiply(n).add(big(5)), context).negate();
    }

    private BigDecimal calculateRight5(BigDecimal n) {
        return big(2).pow(2).divide(big(10).multiply(n).add(big(7)), context).negate();
    }

    private BigDecimal calculateRight6(BigDecimal n) {
        return big(1).divide(big(10).multiply(n).add(big(9)), context);
    }

    private BigDecimal calculateAddend(int n) {
        return calculateLeft(n).multiply(calculateRight(big(n)));
    }

    private BigDecimal big(int n) {
        return new BigDecimal(n);
    }

    private BigDecimal mod(BigDecimal n, BigDecimal divisor) {
        return n.divideAndRemainder(divisor, context)[1];
    }

    private BigDecimal invert(BigDecimal n) {
        return big(1).divide(n, context);
    }

    private BigDecimal round(BigDecimal n, int digits) {
        if (digits < 1) return n;
        return n.setScale(digits - 1, RoundingMode.DOWN);
    }

    private BigDecimal mask(int digits) {
        if (digits < 1) return big(10);
        return invert(big(10).pow(digits - 1));
    }

    private void adjustPrecision(int digits) {
        int precision = context.getPrecision();
        if (digits > precision) context = new MathContext(digits, RoundingMode.HALF_EVEN);
    }
}